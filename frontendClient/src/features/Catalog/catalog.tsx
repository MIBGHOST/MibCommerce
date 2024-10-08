import {useEffect, useState} from "react";
import {Product} from "../../app/Model/product.ts";

export default function Catalog(){
const [products, setProducts] = useState<Product[]>([]);
    useEffect(()=>{
        fetch("http://localhost:8085/api/products")
        .then(response => response.json())
        .then(data => setProducts(data.content));
    },[]);
    return (
        <ul>
        {products.map(product => (
            <div key={product.id}>
            <p>{product.name} : ${product.price}</p>
            <p>Description: {product.description}</p>
            <p>Brand: {product.productBrand} :: Type: {product.productType}</p>
            </div>
        ))}
        </ul>
    )
}