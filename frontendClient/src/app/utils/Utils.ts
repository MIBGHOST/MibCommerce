import {Basket} from "../model/Basket.ts";

export function getBasketFromLocalStorage(): Basket | null{
    const storedBasket = localStorage.getItem("basket");
    if (storedBasket) {
        try{
            const parsedBasket: Basket = JSON.parse(storedBasket);
            return parsedBasket;
        }catch (err){
            console.log('Error Parsing basket: ', err);
            return null;
        }
    }
    return null;
}