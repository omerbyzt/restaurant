import React, {Component} from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios';
import {Link} from "react-router-dom";

class TablePage extends Component {
    state = {
        tableCategoryList: [],
        tableList: [],
        categoryName: "",
        categoryTableNumber: "",
        array:[]
    }

    componentDidMount() {
        const {tableCategoryList} = this.state;
        axios.get('http://localhost:8080/table-category/listall',
            {headers: {Authorization: sessionStorage.getItem('token')}})
            .then(res => {
                this.setState({tableCategoryList: res.data})
            });
    }

    onClickTableCategoryName = (e) => {
        // const{tableList}=this.state;
        // axios.get('http://localhost:8080/table-category/list-table/'+e.id,
        //     {headers:{Authorization: sessionStorage.getItem('token')}})
        //     .then(res => {this.setState({tableList: res.data,
        //     categoryName:e.name})});

         this.setState({
             categoryTableNumber: e.number,
             categoryName : e.name
         })

        this.state.array = [];

        for(let i = 1 ; i<=e.number;i++){
            this.state.array.push(<button className="btn btn-success tableButtons" onClick={() => this.goShoppingList(i)} >{e.name} : {i}</button>)
        }

        return this.state.array;
    }

    goShoppingList = (e) => {
        this.props.history.push("/home")
        sessionStorage.setItem("table", this.state.categoryName + " " + e)
    }

    onClickBackToMenuButton = () => {
        sessionStorage.setItem("table", "No Table")
    }

    render() {
        const {tableCategoryList, tableList, categoryName, categoryTableNumber,array} = this.state
        return (
            <div className="App">
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
                                                <button className="btn btn-info btn-block mb-1"
                                                        onClick={() => this.onClickTableCategoryName(v)}>{v.name}</button>
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
                                        // tableList.map(v => {
                                        //     return (
                                        //         <button className="btn btn-success tableButtons"
                                        //                 onClick={() => this.goShoppingList(v)}>{categoryName} : {v.number}</button>
                                        //     )
                                        // })
                                    }
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

export default TablePage;