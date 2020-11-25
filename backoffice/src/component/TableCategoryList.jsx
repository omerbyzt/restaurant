import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import axios from "axios";
import {Link} from "react-router-dom";

class TableCategoryList extends Component {
    state = {
        tableCategoryList: [],
        isUpdate : false,
        id:"",
        name:""
    }

    componentDidMount() {
        const {tableCategoryList} = this.state

        let uri = "http://localhost:8080/table-category/listall";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    tableCategoryList: data
                })
            })
    }

    onClickDeleteTableCategoryBtn = (e) => {

        axios.delete('http://localhost:8080/table-category/delete/' + e.id,
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({tableCategoryList:this.state.tableCategoryList.filter(table => table.id!==e.id)})});
    }

    onClickUpdateTableCategoryBtn = (e) => {
        this.setState({
            isUpdate : !this.state.isUpdate,
            id:e.id,
            name:e.name
        })
    }

    updateTableCategory = (e) => {
        const{id,name,tableCategoryList} = this.state

        const putTableCategory = {
            id:id,
            name:name
        }

        axios.put('http://localhost:8080/table-category/update/',putTableCategory,
            {headers:{Authorization: sessionStorage.getItem('token')}})
            .then(res => {this.setState({tableCategoryList: this.state.tableCategoryList})});

    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    render() {
        const {tableCategoryList,isUpdate,id,name} = this.state
        return (
            <div>
                <Header/>
                <Link to ="/addtablecategory"><button className="btn btn-success addCategoryButton">+ Add Table Category</button></Link>
                {
                    isUpdate ?
                        <div className = "col-md-12 mb-4 mt-2">
                            <div className="card" align="left">
                                <div className = "card-header" align="center">
                                    <h4>Update Table Category</h4>
                                </div>
                                <div className="card-body">
                                    <form>

                                        <div className="form-group">
                                            <label htmlFor="tablecategoryid">Table Category ID</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder={id}
                                                   name="id"
                                                   id="idInput"
                                                   value={id}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="tablecategoryname">Table Category Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder={name}
                                                   name="name"
                                                   id="nameInput"
                                                   value={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <button className="btn btn-warning btn-block"
                                                onClick={this.updateTableCategory}>Update
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>:null
                }
                <Table striped bordered hover className = "usersTable">
                    <thead>
                    <tr>
                        <th>Table Category ID</th>
                        <th>Table Category Name</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        tableCategoryList.map(v => {
                            return (
                                <tr align="center">
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td>
                                        <button className="btn btn-warning mr-2" onClick={this.onClickUpdateTableCategoryBtn.bind(this,v)}>Update</button>
                                        <button className="btn btn-danger mr-2" onClick={this.onClickDeleteTableCategoryBtn.bind(this,v)}>Delete</button>
                                    </td>
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

export default TableCategoryList;