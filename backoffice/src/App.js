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
import InfoList from "./component/InfoList";
import TableCategoryList from "./component/TableCategoryList";
import AddTableCategory from "./component/AddTableCategory";
import TableList from "./component/TableList";
import AddTable from "./component/AddTable";
import WaiterList from "./component/WaiterList";
import AddWaiter from "./component/AddWaiter";
import AddImage from "./component/AddImage";

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
                        <Route path = "/infolist" component={InfoList}></Route>
                        <Route path = "/tablecategorylist" component={TableCategoryList}></Route>
                        <Route path = "/addtablecategory" component={AddTableCategory}></Route>
                        <Route path = "/tablelist" component={TableList}></Route>
                        <Route path = "/addtable" component={AddTable}></Route>
                        <Route path = "/waiterlist" component={WaiterList}></Route>
                        <Route path = "/addwaiter" component={AddWaiter}></Route>
                        <Route path = "/addimage" component={AddImage}></Route>
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default App;