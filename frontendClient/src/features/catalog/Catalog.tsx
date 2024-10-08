import {useEffect, useState} from "react";
import {Product} from "../../app/model/product.ts";
import ProductList from "./ProductList.tsx";

export default function Catalog(){
const [products, setProducts] = useState<Product[]>([]);
    useEffect(()=>{
        fetch("http://localhost:8085/api/products")
        .then(response => response.json())
        .then(data => setProducts(data.content));
    },[]);
    return (
        <>
            <ul>
            <ProductList products={products} />
            </ul>
        </>
    )
}