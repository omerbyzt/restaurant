import './App.css';
import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import nextId from "react-id-generator";
import axios from 'axios';
import {Link} from "react-router-dom";

class ClientHomePage extends Component {

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
            totalPrice: 0,
            tableName:""
        },
        selectedCategoryName:"",

    }

    constructor(props) {
        super(props);
    }

    componentDidMount() {

        let uri = "http://localhost:8080/category/list-category/";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    categoryList: data
                })
            })
    }

    onClickCategoryName = (category) => {
        console.log(category)

        let uri = 'http://localhost:8080/category/list-products/' + category.id;
        console.log(uri);

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
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

        this.state.totalPayment += param.productPrice;

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
                        totalPrice: param.productPrice,
                        tableName:sessionStorage.getItem("table")
                    }
                }, () => this.setState({shoppingList: [...this.state.shoppingList, this.state.shoppingListObj]})
            )
        }

    }

    clickPlusAmountBtn = (value) => {
        const {totalPrice,totalPayment} = this.state;

        this.state.totalPayment += value.price;

        value.amount += 1;
        value.totalPrice = value.totalPrice + value.price;

        this.setState([{...this.state.shoppingList, [value.shoppingListObjId]: value}])
    }

    clickMinusAmountBtn = (value) => {
        const{totalPayment} = this.state;
        this.state.totalPayment -= value.price;

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
                Authorization: sessionStorage.getItem('token') //the token is a variable which holds the token
            }
        })
        sessionStorage.setItem("table","No Item");
        window.location.reload();
    }

    onClickBackToMenuButton = () => {
        sessionStorage.setItem("table","No Item")
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
                                                            onClick={() => this.onClickCategoryName(v)}>{v.name}</button>
                                                )
                                            }
                                        )
                                    }
                                </div>
                            </div>
                            <Link to="/menu"><button className="btn btn-danger btnPay" onClick={this.onClickBackToMenuButton}>Back to Menu</button></Link>
                        </th>
                        <th className="col-md-6">
                            <div className="card productBigCard">
                                <div className="card-header">
                                    <h4 className="d-inline">Product List
                                    </h4>
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
                                    <div className="card-body shoppingListColumnCardBody">
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
                                    </div>
                                </div>
                                <button className="btn btn-success btnPay"
                                        onClick={() => this.clickPayButton(shoppingList)}>Total:{totalPayment}₺
                                </button>
                            </div>
                        </th>
                    </tr>
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default ClientHomePage;
