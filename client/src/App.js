import React, {Component, useState} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginPage from "./LoginPage";
import MenuPage from "./MenuPage";
import ClientHomePage from "./ClientHomePage";
import TablePage from "./TablePage";

export const AppContext = React.createContext("defaultValue");

class App extends Component {

    state = {
        token: null,
        setToken: this.setToken,
    }
    //This is the method to set the context data.
    setToken = (token) => {
        this.setState({ token });
    }

    render() {
        return (
            <AppContext.Provider value={this.state}>
                <div>
                    <Router>
                        <Switch>
                            <Route path="/" exact component={LoginPage}></Route>
                            <Route path="/menu" component={MenuPage}></Route>
                            <Route path="/home" component={ClientHomePage}></Route>
                            <Route path="/tablepage" component={TablePage}></Route>
                        </Switch>
                    </Router>
                </div>
            </AppContext.Provider>
        );
    }
}

export default App;