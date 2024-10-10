import {useEffect, useState} from "react";
import {Product} from "../../app/model/product.ts";
import ProductList from "./ProductList.tsx";
import agent from "../../app/api/agent.ts";

export default function Catalog(){
const [products, setProducts] = useState<Product[]>([]);
const [loading, setLoading] = useState(true);
    useEffect(()=>{
        agent.Store.list()
            .then((products)=>setProducts(products.content))
            .catch(error=>console.log(error))
            .finally(()=>setLoading(false));
    }, []);
    if(!products) return <h3>Unable to load Products</h3>
    if(loading) return <h3>'Loading Products...'</h3>
    return (
        <>
            <ul>
            <ProductList products={products} />
            </ul>
        </>
    )
}