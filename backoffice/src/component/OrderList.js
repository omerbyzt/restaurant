import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';

class OrderList extends Component {

    state = {
        orderList: []
    }

    componentDidMount() {
        const {orderList} = this.state
/*
        fetch('http://localhost:8080/order/listall')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    orderList: data
                })
            }).catch(e => {
            console.warn("e : ", e);
        });
        */

        let uri = "http://localhost:8080/order/listall";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa('admin:pass3'),
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
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#OrderID</th>
                        <th>#ProductID</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Product Amount</th>
                        <th>Total Price</th>
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