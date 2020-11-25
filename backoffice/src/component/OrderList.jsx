import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import Header from "./Header";

class OrderList extends Component {

    state = {
        orderList: []
    }

    componentDidMount() {
        const {orderList} = this.state

        let uri = "http://localhost:8080/order/listall";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    orderList: data
                })
            })

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
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default OrderList;