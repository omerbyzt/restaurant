import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import Header from "./Header";
import UserContext from "../Context";
import Loading from "./Loading";

class OrderList extends Component {
    static contextType = UserContext;
    state = {
        orderList: [],
        loadingIsVisible:false
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/order/listall";

            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    // 'Authorization': sessionStorage.getItem('token'),
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        orderList: data
                    })
                })
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }


    render() {
        const {orderList} = this.state;
        return (
            <div>
                <Header></Header>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#OrderID</th>
                        <th>#ProductID</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Product Amount</th>
                        <th>Total Price</th>
                        <th>Table</th>
                        <th>Order Date</th>
                        <th>Waiter ID</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        orderList.map(v=>{
                            return(
                                <tr>
                                    <td>{v.orderID}</td>
                                    <td>{v.pId}</td>
                                    <td>{v.name}</td>
                                    <td>{v.price}</td>
                                    <td>{v.amount}</td>
                                    <td>{v.amount*v.price}</td>
                                    <td>{v.tableName}</td>
                                    <td>{v.orderDate}</td>
                                    <td>{v.waiterID}</td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>
                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }
            </div>
        );
    }
}

export default OrderList;