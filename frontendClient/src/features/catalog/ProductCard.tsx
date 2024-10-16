import {Product} from "../../app/model/product.ts";
import {Avatar, Button, Card, CardActions, CardContent, CardHeader, CardMedia, Typography} from "@mui/material";
import {Link} from "react-router-dom";

interface Props {
    product : Product;
}

const extractImageName = (item: Product): string | null => {
    if(item && item.pictureUrl){
        const parts = item.pictureUrl.split('/');
        if(parts.length > 0){
            return parts[parts.length - 1];
        }
    }
    return null;
};
const formatPrice = (price: number) => {
    return new Intl.NumberFormat('en-In', {
        style:'currency',
        currency: 'INR',
        minimumFractionDigits: 2
    }).format(price);
};

export default function ProductCard({product}: Props){
    return (
        <Card>
            <CardHeader avatar={
                <Avatar sx={{backgroundColor: 'secondary.main'}}>
                    {product.name.charAt(0).toUpperCase()}
                </Avatar>
            }
            title={product.name}
            titleTypographyProps={{sx:{fontWeight: 'bold', color:'primary.main'}}}
            />
        <CardMedia
            sx={{ height: 140, backgroundSize: 'contain'}}
            image={"images/products/"+extractImageName(product)}
            title={product.name}
        />
        <CardContent>
            <Typography gutterBottom color='secondary' variant="h5">
                {formatPrice(product.price)}
            </Typography>
            <Typography variant="body2" color="textSecondary">
                {product.productBrand} / {product.productType}
            </Typography>
        </CardContent>
        <CardActions>
            <Button size="small">Add to Cart</Button>
            <Button component={Link} to={`/store/${product.id}`} size="small">View</Button>
        </CardActions>
        </Card>
    )
}