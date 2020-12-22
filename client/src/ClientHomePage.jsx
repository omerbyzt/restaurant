import './App.css';
import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import nextId from "react-id-generator";
import axios from 'axios';
import {Link} from "react-router-dom";
import UserContext from "./Context";
import Loading from "./Loading";

class ClientHomePage extends Component {
    static contextType = UserContext;

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
            tableName: "",
        },
        selectedCategoryName: "",
        loadingIsVisible: false,
        scrollPosition: 0,
        pageNumber: 0,
        selectedCategoryId: 1,
        tempArray:[]
    }


    constructor(props) {
        super(props);
        this.myRef = React.createRef()
    }

    async componentDidMount() {
        const {token} = this.context
        this.setState({loadingIsVisible: true});
        if (localStorage.getItem("token") !== null || token !== "No Token") {
            //TODO:seçilmiş odadan f5 yapınca wiater ve seçili masa?

            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            console.log("null değil")
            const {token, table} = this.context;

            let uri = "http://localhost:8080/category/list-category/";

            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        categoryList: data
                    })
                })

            //
            let uri2 = 'http://localhost:8080/product/listall';

            await fetch(uri2, {
                method: 'get',
                headers: new Headers({
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        content: data
                    })
                })
            //

            let allOrders = ClientHomePage.getOrderListFromStorage();
            let tableName = table
            if (allOrders.length > 0) {
                for (let i = 0; i < allOrders.length; i++) {
                    if (allOrders[i][0].tableName.indexOf(tableName) !== -1) {
                        for (let j = 0; j < allOrders[0].length; j++) {
                            this.state.shoppingList.push(allOrders[i][j]);
                            this.state.totalPayment += allOrders[i][j].totalPrice;
                        }
                        allOrders.splice(i, 1);
                    }
                }
                localStorage.setItem("orderList", JSON.stringify(allOrders))
            }
        } else {
            console.log("null geldi")
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    onClickCategoryName = async (id) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context;

        await axios.get('http://localhost:8080/product/listslice',
            {
                headers: {Authorization: token},
                params: {page: this.state.pageNumber, size: 10, categoryId: id}
            }
        )
            .then(res => {
                this.setState({
                    content: res.data.productDTOList,
                    selectedCategoryId:id
                })
            });

        this.state.tempArray.push(this.state.content);
        console.log(this.state.tempArray)
        this.setState({loadingIsVisible: false});
    }

    onClickAddShoppingList = (param) => {
        const {shoppingList, totalPayment, shoppingListAmounts} = this.state;
        const {table, waiterID} = this.context;
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
                        tableName: table,
                        waiterID: waiterID
                    }
                }, () => this.setState({shoppingList: [...this.state.shoppingList, this.state.shoppingListObj]})
            )
        }

    }

    clickPlusAmountBtn = (value) => {
        const {totalPrice, totalPayment} = this.state;

        this.state.totalPayment += value.price;

        value.amount += 1;
        value.totalPrice = value.totalPrice + value.price;

        this.setState([{...this.state.shoppingList, [value.shoppingListObjId]: value}])
    }

    clickMinusAmountBtn = (value) => {
        const {totalPayment} = this.state;
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

    clickPayButton = async (value) => {
        this.setState({loadingIsVisible: true});
        const {token, setTable, setWaiterID, setWaiterName} = this.context
        // CREATE TABLE OR PAY DIRECTLY
        // if(sessionStorage.getItem("table") === "No Table"){
        console.log(value);

        await axios.post('http://localhost:8080/order/add', value, {
            headers: {
                Authorization: token //the token is a variable which holds the token
            }
        })
        window.alert("Ödeme Alındı");
        // }else{
        //     let orderList = ClientHomePage.getOrderListFromStorage();
        //     if (this.state.shoppingList.length>0){
        //         orderList.push(this.state.shoppingList);
        //         localStorage.setItem("orderList",JSON.stringify(orderList));
        //     }
        //     window.alert("Table Created");
        // }

        // sessionStorage.setItem("table", "No Table");
        // sessionStorage.setItem("waiterID", "-1");
        // sessionStorage.setItem("waiterName", "No Waiter");

        setTable("No Table");
        setWaiterName("No Waiter");
        setWaiterID("-1");
        this.setState({loadingIsVisible: false});
        this.props.history.push("/menu")
    }

    static getOrderListFromStorage = () => {
        let orderList;

        if (localStorage.getItem("orderList") === null) {
            orderList = [];
        } else {
            orderList = JSON.parse(localStorage.getItem("orderList"));
        }

        return orderList;
    }

    onClickBackToMenuButton = () => {
        const {setTable, setWaiterID, setWaiterName} = this.context
        let orderList = ClientHomePage.getOrderListFromStorage();
        if (this.state.shoppingList.length > 0) {
            orderList.push(this.state.shoppingList);
            localStorage.setItem("orderList", JSON.stringify(orderList));
        }
        // sessionStorage.setItem("table", "No Table")
        // sessionStorage.setItem("waiterID", "-1")
        // sessionStorage.setItem("waiterName", "No Waiter")

        setTable("No Table");
        setWaiterName("No Waiter");
        setWaiterID("-1");
    }

    onScroll = async () => {
        const {scrollPosition, selectedCategoryId} = this.state
        const scrollTop = this.myRef.current.scrollTop
        this.setState({scrollPosition: scrollTop})

        if (scrollPosition > (this.state.pageNumber+1)*800) {
            this.state.pageNumber++;
            await this.onClickCategoryName(selectedCategoryId)
            this.setState({scrollPosition: 0})
        }
        console.log(scrollPosition)
        console.log(this.state.pageNumber)
    }

    render() {
        const {content, categoryList, shoppingList, totalPayment, tempArray} = this.state;
        const {waiterName, table} = this.context;
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
                                                    <button className="btn btn-info btn-block mb-1 categoryButtonCss"
                                                            onClick={() => this.onClickCategoryName(v.id)}>
                                                        {v.name}
                                                        <br/>
                                                        <img src={'data:image/png;base64,' + v.mediaDTO.fileContent}
                                                             width="87" style={{margin: 10}}/>
                                                    </button>
                                                )
                                            }
                                        )
                                    }
                                </div>
                            </div>
                            <button className="btn btn-default btnPay"><b>Waiter
                                : </b>{waiterName}</button>
                            <Link to="/tablepage">
                                <button className="btn btn-default btnPay"><b>Selected Table
                                    : </b>{table}</button>
                            </Link>
                            <Link to="/menu">
                                <button className="btn btn-danger btnPay" onClick={this.onClickBackToMenuButton}>Back to
                                    Menu
                                </button>
                            </Link>
                        </th>
                        <th className="col-md-6">
                            <div className="card productBigCard">
                                <div className="card-header">
                                    <h4 className="d-inline">Product List
                                    </h4>
                                </div>
                                <div className="card-body productBigCardBody" ref={this.myRef} onScroll={this.onScroll}>
                                    {
                                        content.map(v => {
                                        // tempArray.map(v => {
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
                                                        <button className="btn btn-warning btn-block addListButton"
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
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default ClientHomePage;
