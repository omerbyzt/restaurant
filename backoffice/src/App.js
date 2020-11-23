import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import OrderList from "./component/OrderList";
import UserLogin from "./component/UserLogin";
import FirstPage from "./component/FirstPage";
import './App.css';
import AddProduct from "./component/AddProduct";
import AddUser from "./component/AddUser";
import UserList from "./component/UserList";
import AuthList from "./component/AuthList";
import CategoryList from "./component/CategoryList";
import AddCategory from "./component/AddCategory";

class App extends Component {
    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route path = "/orderlist" component={OrderList}></Route>
                        <Route path = "/" exact component={UserLogin}></Route>
                        <Route path = "/home" component={FirstPage}></Route>
                        <Route path = "/addproduct" component={AddProduct}></Route>
                        <Route path = "/adduser" component={AddUser}></Route>
                        <Route path = "/userlist" component={UserList}></Route>
                        <Route path = "/authlist" component={AuthList}></Route>
                        <Route path = "/categorylist" component={CategoryList}></Route>
                        <Route path = "/addcategory" component={AddCategory}></Route>
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default App;