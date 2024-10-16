import {Container, createTheme, CssBaseline, ThemeProvider} from "@mui/material";
import Header from "./Header.tsx";
import {useState} from "react";
import {Outlet} from "react-router-dom";

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
            <CssBaseline />
            <Header darkMode={darkMode} handleThemeChange={handleThemeChange}/>
            <Container sx={{mt:10}}>
                <Outlet/>
            </Container>
        </ThemeProvider>
    )
}

export default App
