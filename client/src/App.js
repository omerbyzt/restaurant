import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginPage from "./LoginPage";
import MenuPage from "./MenuPage";
import ClientHomePage from "./ClientHomePage";

class App extends Component {
    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route path = "/" exact component={LoginPage}></Route>
                        <Route path = "/menu" component={MenuPage}></Route>
                        <Route path = "/home" component={ClientHomePage}></Route>
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default App;