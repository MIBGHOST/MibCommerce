import {Divider, Grid, Table, TableBody, TableCell, TableContainer, TableRow, Typography} from "@mui/material";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Product} from "../../app/model/product";
import agent from "../../app/api/agent.ts";
import NotFound from "../../app/errors/NotFound.tsx";

export default function ProductDetails(){
    const {id} = useParams<{id:string}>();
    const [product, setProduct] = useState<Product|null>();
    const [loading, setLoading] = useState(true);
    const extractImageName = (item: Product): string | null => {

        if(item && item.pictureUrl){
            const parts = item.pictureUrl.split("/");
            if(parts.length > 0){
                return parts[parts.length - 1];
            }
        }
        return null;
    };
    const formatPrice = (price: number): string => {
        return new Intl.NumberFormat('en-In', {
            style:'currency',
            currency: 'INR',
            minimumFractionDigits: 2
        }).format(price);
    };
    useEffect(() => {
        if (typeof id === "string") {
            agent.Store.details(parseInt(id))
                .then(response => setProduct(response))
                .catch(e => console.log(e))
                .finally(() => setLoading(false));
        }
    }, [id]);
    if(loading) return <h3>Loading Products...</h3>
    if(!product) return <NotFound/>

    return (
        <Grid container spacing={6}>
        <Grid item xs={6}>
            <img src={"/images/products/"+extractImageName(product)} alt={product.name} style={{width:'100%'}}/>
        </Grid>
        <Grid item xs={6}>
            <Typography variant='h3'>{product.name}</Typography>
            <Divider sx={{mb:2}}/>
            <Typography gutterBottom color='secondary' variant='h4'>{formatPrice(product.price)}</Typography>
            <TableContainer>
                <Table>
                    <TableBody>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>{product.name}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Description</TableCell>
                            <TableCell>{product.description}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Brand</TableCell>
                            <TableCell>{product.productBrand}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Type</TableCell>
                            <TableCell>{product.productType}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
        </Grid>
    )
}