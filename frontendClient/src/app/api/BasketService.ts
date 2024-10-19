import axios from "axios";
import {Basket, BasketItem, BasketTotals} from "../model/basket.ts";
import {Product} from "../model/product.ts";
import {Dispatch, nanoid} from "@reduxjs/toolkit";
import {setBasket} from "../../features/basket/BasketSlice.ts";

class BasketService {
    apiUrl = "http://localhost:8085/api/baskets";

    async getAllBaskets() {
        try{
            const response = await axios.get<Basket>(`${this.apiUrl}`);
            return response.data;
        }catch(err){
            throw new Error("Error getting all baskets" + err);
        }
    }

    async getBasket(){
        try{
            const basket = localStorage.getItem("basket");
            if(basket){
                return JSON.parse(basket) as Basket;
            }else{
                throw new Error("No basket found.");
            }
        }catch (error){
            throw new Error("Failed to retrieve basket." + error);
        }
    }

    async addItemsToBasket(item: Product, quantity=1, dispatch: Dispatch){
        try{
            let basket = this.getCurrentBasket();
            if(!basket){
                basket = await this.createBasket();
            }
            const itemToAdd = this.mapProductToBasket(item);
            basket.items = this.upsertItems(basket.items, itemToAdd, quantity);
            await this.setBasket(basket, dispatch);
            const totals = this.calculateTotals(basket);
            return {basket, totals};
        }catch (error){
            throw new Error("Error adding items to Basket." + error);
        }
    }

    private getCurrentBasket(){
        const basket = localStorage.getItem("basket");
        return basket ? JSON.parse(basket) : null;
    }

    private async createBasket(): Promise<Basket> {
        try{
            const newBasket: Basket = {
                id: nanoid(),
                items: []
            }
            localStorage.setItem('basket_id', newBasket.id);
            return newBasket;
        }catch (error){
            throw new Error("Failed to create Basket." + error);
        }
    }

    private mapProductToBasket(item: Product): BasketItem {
        return {
            id: item.id,
            name: item.name,
            description: item.description,
            pictureUrl: item.pictureUrl,
            price: item.price,
            productBrand: item.productBrand,
            productType: item.productType,
            quantity: 0,
        }
    }

    private upsertItems(items: BasketItem[], itemToAdd: BasketItem, quantity: number): BasketItem[] {
        const existingItems = items.find(x => x.id = itemToAdd.id);
        if(existingItems){
            existingItems.quantity = quantity;
        }else{
            itemToAdd.quantity = quantity;
            items.push(itemToAdd);
        }
        return items;
    }

    async setBasket(basket: Basket, dispatch: Dispatch){
        try{
            await axios.post<Basket>(this.apiUrl, basket);
            localStorage.setItem('basket', JSON.stringify(basket));
            dispatch(setBasket(basket));
        }catch (error){
            throw new Error("Error updating items to Basket." + error);
        }
    }
    async incrementItemQuantity(itemId: number, quantity: number=1, dispatch: Dispatch){
        const basket = this.getCurrentBasket();
        if(basket){
            const item = basket.items.find((x: {id: number}) => x.id = itemId);
            if(item){
                item.quantity += quantity;
                if(item.quantity<1){
                    item.quantity = 1;
                }
                await this.setBasket(basket, dispatch);
            }
        }
    }

    async decrementItemQuantity(itemId: number, quantity: number=1, dispatch: Dispatch){
        const basket = this.getCurrentBasket();
        if(basket){
            const item = basket.items.find((x: {id: number}) => x.id = itemId);
            if(item && item.quantity > 1){
                item.quantity -= quantity;
                await this.setBasket(basket, dispatch);
            }
        }
    }

    async remove(itemId:number, dispatch: Dispatch){
        const basket = this.getCurrentBasket();
        if(basket){
            const itemIndex = basket.items.findIndex((p: { id: number; })=>p.id === itemId);
            if(itemIndex !== -1){
                basket.items.splice(itemIndex, 1);
                await this.setBasket(basket, dispatch);
            }
            if(itemIndex === 0){
                localStorage.removeItem('basket_id');
                localStorage.removeItem('basket');
            }
        }
    }
    async deleteBasket(basketID: string): Promise<void> {
        try{
            await axios.delete(`${this.apiUrl}/${basketID}`);
        }catch (error){
            throw new Error("Error deleting items to Basket." + error);
        }
    }

    private calculateTotals(basket: Basket): BasketTotals {
        const shipping = 0;
        const subtotals = basket.items.reduce((acc,item) => acc+(item.price*item.quantity), 0);
        const totals = shipping + subtotals;
        return {shipping, subtotals, totals};
    }
}

export default new BasketService();