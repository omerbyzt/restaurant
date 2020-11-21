import './App.css';
import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import nextId from "react-id-generator";
import axios from 'axios';
import LoginPage from "./LoginPage";
import MenuPage from "./MenuPage";

class App extends Component {

    state = {
        content: [],
        categoryList: [],
        shoppingList: [],
        shoppingListAmounts: [],
        totalPayment: 0,

        shoppingListObj: {
            shoppingListObjId: 0,
            name: "",
            price: 0,
            amount: 0,
            pId: 0,
            totalPrice: 0
        }
    }

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        /*
               const {categoryList} = this.state
               fetch('http://localhost:8080/product/listcategory')
                   .then(response => response.json())
                   .then(data => {
                       this.setState({
                           categoryList: data
                       })
                   }).catch(e => {
                   console.warn("e : ", e);
               });
       */

        let uri = "http://localhost:8080/product/listcategory/";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa('admin:pass3'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    categoryList: data
                })
            })

        /*
        //PUT METODU AXIOS
                var axios = require('axios');
                var data = JSON.stringify({"userName":"omer","password":"2","role":"admin"});

                var config = {
                    method: 'post',
                    url: 'http://localhost:8080/user/add',
                    headers: {
                        'Authorization': 'Basic dXNlcjI6cGFzczI=',
                        'Content-Type': 'application/json',
                        'Cookie': 'JSESSIONID=69525F6D413852513B29B298E8E371E7'
                    },
                    data : data
                };
                axios(config)
                    .then(function (response) {
                        console.log(JSON.stringify(response.data));
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

        */
        /*
        const newUser = {
            userName:"omer2",
            password:"1234556",
            role:"başbakan"
        }
        axios.delete('http://localhost:8080/user/delete/1',{
            headers: {
                Authorization: 'Basic dXNlcjI6cGFzczI=' //the token is a variable which holds the token
            },
            data:JSON.stringify({"userName":"omer2","password":"2234234","role":"yihu"})
        })
*/
    }

    onClickCategoryName = (category) => {
        console.log(category)

        let uri = 'http://localhost:8080/product/listcategory/' + category;
        console.log(uri);

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa('admin:pass3'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    content: data
                })
            })
    }

    onClickAddShoppingList = (param) => {
        const {shoppingList, totalPayment, shoppingListAmounts} = this.state;

        if (this.state.shoppingList.filter(shoppingListObj => shoppingListObj.pId == param.productID).length > 0) {
            var shoppingListObj = this.state.shoppingList.filter(shoppingListObj => shoppingListObj.pId == param.productID)
            shoppingListObj[0].amount += 1;
            shoppingListObj[0].totalPrice = shoppingListObj[0].totalPrice + shoppingListObj[0].price;

            this.setState([{...this.state.shoppingList, [shoppingListObj[0].pId]: shoppingListObj[0]}])


        } else {
            this.setState({
                    shoppingListObj: {
                        shoppingListObjId: nextId(),
                        pId: param.productID,
                        name: param.productName,
                        price: param.productPrice,
                        amount: 1,
                        totalPrice: param.productPrice
                    }
                }, () => this.setState({shoppingList: [...this.state.shoppingList, this.state.shoppingListObj]})
            )
        }

        // console.log(shoppingListAmounts)
    }

    clickPlusAmountBtn = (value) => {
        const {totalPrice} = this.state;

        value.amount += 1;
        value.totalPrice = value.totalPrice + value.price;

        this.setState([{...this.state.shoppingList, [value.shoppingListObjId]: value}])
    }

    clickMinusAmountBtn = (value) => {
        value.amount -= 1;
        value.totalPrice = value.totalPrice - value.price;

        if (value.amount == 0) {
            this.setState({
                shoppingList: this.state.shoppingList.filter(obj => obj.pId !== value.pId)
            })
        } else {
            this.setState([{...this.state.shoppingList, [value.shoppingListObjId]: value}])
        }
    }

    clickPayButton = (value) => {
        console.log(value);

        axios.post('http://localhost:8080/order/add', value, {
            headers: {
                Authorization: 'Basic ' + btoa('admin:pass3') //the token is a variable which holds the token
            }
        })
    }

    render() {
        const {content, categoryList, shoppingList, totalPayment} = this.state;
        return (
            <div className="App">
                <Table bordered>
                    <tbody>
                    <tr>
                        <th>
                            <div className="card categoryColumn">
                                <div className="card-header">
                                    <h4 className="d-inline">Category List</h4>
                                </div>
                                <div className="card-body">
                                    {
                                        categoryList.map(v => {
                                                return (
                                                    <button className="btn btn-info btn-block mb-1"
                                                            onClick={() => this.onClickCategoryName(v)}>{v}</button>
                                                )
                                            }
                                        )
                                    }
                                </div>
                            </div>
                        </th>
                        <th className="col-md-6">
                            <div className="card productBigCard">
                                <div className="card-header">
                                    <h4 className="d-inline">Product List</h4>
                                </div>
                                <div className="card-body productBigCardBody">
                                    {
                                        content.map(v => {
                                            return (
                                                <div className="card productCards">
                                                    <div className="card-header">
                                                        <h4 className="d-inline">{v.productName}</h4>
                                                    </div>
                                                    <div className="card-body">
                                                        <p className="card-text">Product Description : {v.productID}</p>
                                                        <p className="card-text">Product Description
                                                            : {v.productDesc}</p>
                                                        <p className="card-text">Product Category
                                                            : {v.productCategory}</p>
                                                        <p className="card-text">Product Price : {v.productPrice}</p>
                                                        <button className="btn btn-warning btn-block"
                                                                onClick={() => this.onClickAddShoppingList(v)}>Add List
                                                            +
                                                        </button>
                                                    </div>
                                                </div>
                                            )
                                        })
                                    }
                                </div>
                            </div>
                        </th>
                        <th>
                            <div>
                                <div className="card shoppingListColumn">
                                    <div className="card-header">
                                        <h4 className="d-inline">Shopping List</h4>
                                    </div>
                                    <div className="card-body">
                                        {
                                            shoppingList.map(v => {
                                                    return (
                                                        <Table>
                                                            <tbody>
                                                            <tr>
                                                                <th>
                                                                    <button className="btn btn-danger"
                                                                            onClick={() => this.clickMinusAmountBtn(v)}>-
                                                                    </button>
                                                                </th>
                                                                <th>
                                                                    <tr>{v.amount}x</tr>
                                                                    <tr><label>{v.name}</label></tr>
                                                                    <tr>Price : {v.totalPrice}</tr>
                                                                </th>
                                                                <th>
                                                                    <button className="btn btn-success"
                                                                            onClick={() => this.clickPlusAmountBtn(v)}>+
                                                                    </button>
                                                                </th>
                                                            </tr>
                                                            </tbody>
                                                        </Table>
                                                    )
                                                }
                                            )
                                        }
                                        <button className="btn btn-danger btn-block btnPay"
                                                onClick={() => this.clickPayButton(shoppingList)}>Total:{totalPayment}₺
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </th>
                    </tr>
                    </tbody>
                </Table>

            </div>
        );
    }

}

export default App;
