import {Product} from "../../app/model/product.ts";
import {Grid, Grid2} from "@mui/material";
import ProductCard from "./ProductCard.tsx";

interface Props {
    products: Product[];
}

export default function ProductList({products}: Props){
    return(
        <Grid2 container={true} spacing={4}>
            {products.map((product)=> (
                <Grid item xs={3} key={product.id}>
                    <ProductCard product={product}/>
                </Grid>
                )
            )}
        </Grid2>
    );
}