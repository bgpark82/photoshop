import "../css/index.css";
import { createMuiTheme, MuiThemeProvider } from "@material-ui/core";
import { purple } from "@material-ui/core/colors";
import Layout from "../components/Layout";
import store from "../store/configStore";
import { Provider } from "react-redux";

const theme = createMuiTheme({
  palette: {
    primary: {
      main: "#fefefe",
    },
    secondary: purple,
  },
  typography: {
    fontFamily: "Quicksand",
    fontWeightLight: 400,
    fontWeightRegular: 500,
    fontWeightMedium: 600,
    fontWeightBold: 700,
  },
});

export default function MyApp({ Component, pageProps }) {
  return (
    <MuiThemeProvider theme={theme}>
      <Provider store={store}>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </Provider>
    </MuiThemeProvider>
  );
}
