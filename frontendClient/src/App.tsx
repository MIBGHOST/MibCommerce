import {useEffect, useState} from "react";

function App() {
    const [products, setProducts] = useState([]);
    useEffect(()=>{
        const fetchData = async () =>{
            try{
                const response = await fetch("http://localhost:8085/api/products");
                if(!response.ok){
                    throw new Error('Failed to fetch products');
                }
                const data = await response.json();
                setProducts(data.content);
            }catch (error) {
                console.log('Error fetching data: ', error);
            }
        };
        fetchData();
    },[]);
    return(
        <div>
            <h1>MibCommerce</h1>
            {products.map(product => (
                <div key={product.id}>
                    <p>{product.name} : ${product.price}</p>
                    <p>Description: {product.description}</p>
                    <p>Brand: {product.productBrand} :: Type: {product.productType}</p>
                </div>
            ))}
        </div>
    )
}

export default App
