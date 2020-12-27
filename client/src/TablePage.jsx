import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios';
import {Link} from "react-router-dom";
import {Modal} from "react-bootstrap";
import ClientHomePage from "./ClientHomePage";
import UserContext from "./Context";
import Loading from "./Loading";

class TablePage extends Component {
    static contextType = UserContext;

    state = {
        tableCategoryList: [],
        tableList: [],
        categoryName: "",
        categoryTableNumber: "",
        array: [],
        showModal: false,
        waiterList: [],
        selectedTable: "",
        showClearTableModal: false,
        modalObjectList: [],
        tableOrderList: [],
        loadingIsVisible: false
    }

    async componentDidMount() {
        //TODO buralar hep gözden geçecek
        const {token} = this.context
        this.setState({loadingIsVisible: true});
        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            const {tableCategoryList, waiterList} = this.state;
            const {token} = this.context;
            await axios.get('http://localhost:8080/table-category/listall',
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({tableCategoryList: res.data})
                });

            axios.get('http://localhost:8080/waiter/list-waiters',
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({waiterList: res.data})

                });

        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    onClickTableCategoryName = async (e) => {
        this.setState({
            categoryTableNumber: e.number,
            categoryName: e.name,
            loadingIsVisible: true
        })
        console.log(this.state.waiterList)
        this.state.array = [];

        let orderString;
        let orderList;
        if (localStorage.getItem("orderList") === null) {
            orderString = "";
        } else {
            orderString = localStorage.getItem("orderList");
            orderList = JSON.parse(localStorage.getItem("orderList"));
        }

        let count = 0;
        for (let i = 1; i <= e.number; i++) {//loop for selected category tables
            count = 0;
            let temp = `"${e.name} ${i}"`
            if (orderString.indexOf(temp) !== -1) {
                for (let j = 0; j < orderList.length; j++) {
                    for (let k = 0; k < orderList[j].length; k++) {
                        let tmp = JSON.stringify(orderList[j][k]);
                        if (tmp.indexOf(temp) !== -1) {
                            let startKey = "\"amount\":";
                            let amountStartIndex = tmp.indexOf(startKey) + startKey.length;
                            let endKey = ",\"totalPrice";
                            let amountLastIndex = tmp.indexOf(endKey);
                            let productAmount = Number(tmp.substring(amountStartIndex, amountLastIndex));
                            count += productAmount;
                        }
                    }
                }
                await this.state.array.push(<button className="btn btn-danger tableButtons"
                                                    onClick={() => this.fullTableModal(i)}>
                    <h4>{e.name} : {i}</h4> {count}
                </button>)
            } else {
                await this.state.array.push(<button className="btn btn-success tableButtons"
                                                    onClick={() => this.emptyTableModal(i)}>{e.name} : {i}</button>)
            }
        }
        this.setState({loadingIsVisible: false});
        return this.state.array;
    }

    handleFullTableModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    handleEmptyTableModal = () => {
        this.setState({showClearTableModal: !this.state.showClearTableModal})
    }

    emptyTableModal = (e) => {
        this.setState({
            showModal: !this.state.showModal,
            selectedTable: e
        })
    }

    fullTableModal = async (e) => {
        const {modalObjectList, tableOrderList} = this.state;

        const {table, setTable} = this.context;
        this.setState({
            showClearTableModal: !this.state.showClearTableModal,
            loadingIsVisible: true
        })
        this.state.selectedTable = e;

        sessionStorage.setItem("table", this.state.categoryName + " " + this.state.selectedTable);//session
        //await setTable(this.state.categoryName + " " + this.state.selectedTable);//context

        let orderList = JSON.parse(localStorage.getItem("orderList"))

        modalObjectList.length = 0;

        let tableString = sessionStorage.getItem("table")//session

        //let tableString = table;//context
        console.log(table)
        for (let i = 0; i < orderList.length; i++) {
            for (let j = 0; j < orderList[i].length; j++) {
                if (orderList[i][j].tableName === tableString) {
                    tableOrderList.push(orderList[i][j])
                    let asd = {name: orderList[i][j].name, amount: orderList[i][j].amount, price: orderList[i][j].price}
                    modalObjectList.push(asd);
                }
            }
        }
        this.setState({loadingIsVisible: false});
    }

    goShoppingList = (e) => {
        const {setTable, setWaiterID, setWaiterName} = this.context;
        this.props.history.push("/home")
        // sessionStorage.setItem("table", this.state.categoryName + " " + this.state.selectedTable)
        // sessionStorage.setItem("waiterID", e.id)
        // sessionStorage.setItem("waiterName", e.name)

        setTable(this.state.categoryName + " " + this.state.selectedTable);
        setWaiterID(e.id);
        setWaiterName(e.name);
    }

    onClickBackToMenuButton = () => {
        const {setTable} = this.context;
        // sessionStorage.setItem("table", "No Table")
        setTable("No Table");
    }

    clearTable = () => {
        const {table} = this.context;
        const tableSession = sessionStorage.getItem("table");
        let orderList = ClientHomePage.getOrderListFromStorage();

         orderList.forEach(function (order, index) {
            let firstOrderString = JSON.stringify(order[0])
            let sessionTableString = "\"" + tableSession + "\"";//table-tableSession
            if (firstOrderString.indexOf(sessionTableString) !== -1) {
                orderList.splice(index, 1);
            }
        })
        localStorage.setItem("orderList", JSON.stringify(orderList))
        //TODO: masa silinmesi için onay al silindi mesajı ver.
        this.props.history.push("/menu")
    }

    payTable = async (value) => {
        this.setState({loadingIsVisible: true});
        const {token, setWaiterID, setWaiterName, table, setTable} = this.context;
        console.log(value);

        await axios.post('http://localhost:8080/order/add', value, {
            headers: {
                Authorization: token //the token is a variable which holds the token
            }
        })
        let orderList = ClientHomePage.getOrderListFromStorage();

        // sessionStorage.setItem("waiterID","-1");
        // sessionStorage.setItem("waiterName","No Waiter");
        setWaiterID("-1");
        setWaiterName("No Waiter");
        const tableSession = sessionStorage.getItem("table");

        orderList.forEach(function (order, index) {
            let firstOrderString = JSON.stringify(order[0])
            let sessionTableString = "\"" + tableSession + "\"";//table-tableSession
            if (firstOrderString.indexOf(sessionTableString) !== -1) {
                orderList.splice(index, 1);
            }
        })

        localStorage.setItem("orderList", JSON.stringify(orderList))
        window.alert("Payment Received");
        sessionStorage.setItem("table", "No Table");//session
        setTable("No Table");
        this.setState({loadingIsVisible: false});
        this.props.history.push("/menu")
    }

    goTable = () => {
        const {setTable} = this.context;
        // sessionStorage.setItem("table" ,this.state.categoryName + " " + this.state.selectedTable)
        setTable(this.state.categoryName + " " + this.state.selectedTable);
        sessionStorage.setItem("table", this.state.categoryName + " " + this.state.selectedTable);
        this.setState({
            showModal: !this.state.showModal
        })
        //this.props.history.push("/home")
    }

    render() {
        const {tableCategoryList, tableList, categoryName, categoryTableNumber, array, waiterList, modalObjectList} = this.state
        return (
            <div className="App">
                <Modal show={this.state.showModal} onHide={() => this.handleFullTableModal()}>
                    <Modal.Header closeButton>Modal Header</Modal.Header>
                    <Modal.Body className="modelBody">
                        {
                            waiterList.map(v => {
                                return (
                                    <button className="btn btn-outline-info btn-block mb-1" onClick={() => this.goShoppingList(v)}>
                                        <div className="row">
                                            <div className="col-md-6 my-auto" align="left">
                                                {v.name}
                                            </div>
                                            <div className="col-md-6 my-auto" align="right">
                                                <img src={'data:image/png;base64,' + v.mediaDTO.fileContent} width="100"
                                                     style={{margin: 10}}/>
                                            </div>
                                        </div>
                                    </button>
                                )
                            })
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() => this.handleFullTableModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.showClearTableModal} onHide={() => this.handleEmptyTableModal()}>
                    <Modal.Header closeButton><h4>Table Detail</h4></Modal.Header>
                    <Modal.Body align="center">

                        <Table>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Amount</th>
                                <th>Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                modalObjectList.map(v => {
                                    return (
                                        <tr>
                                            <td>{v.name}</td>
                                            <td>{v.amount}</td>
                                            <td>{v.price * v.amount}</td>
                                        </tr>
                                    )
                                })
                            }
                            </tbody>
                        </Table>
                        <button className="btn btn-warning btn-lg mr-2" onClick={() => this.goTable()}>Go Table</button>
                        <button className="btn btn-success btn-lg mr-2"
                                onClick={() => this.payTable(this.state.tableOrderList)}>Pay Table
                        </button>
                        <button className="btn btn-danger btn-lg" onClick={() => this.clearTable()}> Clear Table
                        </button>
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() => this.handleEmptyTableModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>
                <Table bordered>
                    <tbody>
                    <tr>
                        <th>
                            <div className="card tableCategoryCard">
                                <div className="card-header">
                                    <h4 className="d-inline">Table Categories</h4>
                                </div>
                                <div className="card-body">
                                    {
                                        tableCategoryList.map(v => {
                                            return (
                                                <button className="btn btn-info btn-block mb-1 categoryButtonCss"
                                                        onClick={() => this.onClickTableCategoryName(v)}>{v.name}
                                                        <br/>
                                                    <img src={'data:image/png;base64,' + v.media.fileContent}
                                                         width="87" style={{margin: 10}}/>
                                                </button>
                                            )
                                        })
                                    }
                                </div>

                            </div>
                            <Link to="/menu">
                                <button className="btn btn-danger backToMenuBtn"
                                        onClick={this.onClickBackToMenuButton}>Back To Menu
                                </button>
                            </Link>
                        </th>
                        <th className="col-md-6">
                            <div className="card tablesBigCard">
                                <div className="card-header">
                                    <h4 className="d-inline">Tables</h4>
                                </div>
                                <div className="card-body tablesBigCardBody" id="tables-body">
                                    {
                                        this.state.array
                                    }
                                </div>
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

export default TablePage;