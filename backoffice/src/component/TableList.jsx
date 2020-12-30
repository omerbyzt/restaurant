import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";
import UserContext from "../Context";

class TableList extends Component {
    static contextType = UserContext;
    state = {
        tableList : [],
        isUpdate:false,
        id:"",
        number:"",
        category:"",
        token:this.context
    }
    componentDidMount() {
        axios.get('http://localhost:8080/table/listall',
            {headers:{Authorization: this.state.token}})
            .then(res => {this.setState({tableList: res.data})});
    }

    onClickDeleteTableBtn = (e) => {
        console.log(e.id)
        axios.delete('http://localhost:8080/table/delete/' + e.id,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers:{Authorization: this.state.token}})
            .then(res => {this.setState({tableList:this.state.tableList.filter(table => table.id!==e.id)})});
    }

    onClickUpdateTableBtn = (e) => {
        this.setState({
            isUpdate:!this.state.isUpdate,
            id:e.id,
            number:e.number,
            category:e.category
        })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    updateTable = (e) => {
        const{id,number,name,category} = this.state;

        const putTable ={
            id:id,
            number:number,
            category:category
        }

        axios.put('http://localhost:8080/table/update/',putTable,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers:{Authorization: this.state.token}})
            .then(res => {this.setState({tableList: this.state.tableList})});
    }

    filterList = (e) => {
        this.setState({
            tableList:this.state.tableList.filter(
                productByFilter => productByFilter.category == e
            )
        })
    }

    render() {
        const{tableList,isUpdate,id,number,category} = this.state;
        return (
            <div>
                <Header/>
                <Link to ="/addtable"><button className="btn btn-success addCategoryButton">+ Add Table</button></Link>
                {
                    isUpdate ?
                        <div className = "col-md-12 mb-4 mt-2">
                            <div className="card" align="left">
                                <div className="card-header">
                                    <h4 className="d-imline">Update Table</h4>
                                </div>
                                <div className="card-body">
                                    <form>

                                        <div className="form-group">
                                            <label htmlFor="id">Table ID</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder={id}
                                                   name="id"
                                                   id="idInput"
                                                   value={id}
                                                   disabled={id}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="number">Table Number</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder={number}
                                                   name="number"
                                                   id="numberInput"
                                                   value={number}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="category">Table Category</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder={category}
                                                   name="category"
                                                   id="categoryInput"
                                                   value={category}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <button className="btn btn-warning btn-block"
                                                onClick={this.updateTable}>Update
                                        </button>

                                    </form>
                                </div>
                            </div>
                        </div>:null
                }

                <Table striped bordered hover className = "usersTable">
                    <thead>
                    <tr>
                        <th>Table ID</th>
                        <th>Table Number</th>
                        <th>Table Category</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                     tableList.map(v => {
                         return(
                             <tr align="center">
                                 <td>{v.id}</td>
                                 <td>{v.number}</td>
                                 <td>
                                     <button className="btn btn-link" onClick={()=> this.filterList(v.category)}>{v.category}</button>
                                 </td>
                                 <td>
                                     <button className="btn btn-warning mr-2" onClick={this.onClickUpdateTableBtn.bind(this,v)}>Update</button>
                                     <button className="btn btn-danger mr-2" onClick={this.onClickDeleteTableBtn.bind(this,v)}>Delete</button>
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

export default TableList;