import {Container, createTheme, CssBaseline, ThemeProvider} from "@mui/material";
import Header from "./Header.tsx";
import {useState} from "react";
import {Outlet} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

function App() {
    const [darkMode, setDarkMode] = useState(false);
    const paletteType = darkMode ? "dark" : "light";

    const theme = createTheme({
        palette:{
            mode:paletteType,
        }
    });
    function handleThemeChange() {
        setDarkMode(!darkMode);
    }
    return(
        <ThemeProvider theme={theme}>
            <ToastContainer position='top-center' hideProgressBar theme='colored'/>
            <CssBaseline />
            <Header darkMode={darkMode} handleThemeChange={handleThemeChange}/>
            <Container sx={{mt:10}}>
                <Outlet/>
            </Container>
        </ThemeProvider>
    )
}

export default App
