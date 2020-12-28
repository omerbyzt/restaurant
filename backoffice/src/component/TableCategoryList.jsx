import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import axios from "axios";
import {Link} from "react-router-dom";
import UserContext from "../Context";
import Loading from "./Loading";
import ReactToExcel from 'react-html-table-to-excel';

class TableCategoryList extends Component {
    static contextType = UserContext;
    state = {
        tableCategoryList: [],
        isUpdate: false,
        id: "",
        name: "",
        number: "",
        loadingIsVisible: false,
        mediaList:"",
        selectedMediaId:"",
        selectedMediaName:"Select Media",
        selectedMediaURL:""
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/table-category/listall";
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
                        tableCategoryList: data
                    })
                })

            await axios.get('http://localhost:8080/file/list',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({mediaList: res.data})
                });
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    onClickDeleteTableCategoryBtn = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        await axios.delete('http://localhost:8080/table-category/delete/' + e.id,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({tableCategoryList: this.state.tableCategoryList.filter(table => table.id !== e.id)})
            });
        this.setState({loadingIsVisible: false});
    }

    onClickUpdateTableCategoryBtn = (e) => {
        this.setState({
            isUpdate: !this.state.isUpdate,
            id: e.id,
            name: e.name,
            number: e.number
        })
        console.log(e);
    }

    updateTableCategory = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {id, name, tableCategoryList, number} = this.state

        const putMedia = {
            id:this.state.selectedMediaID,
            name:this.state.selectedMediaName,
            fileContent:this.state.selectedMediaURL
        }

        const putTableCategory = {
            id: id,
            name: name,
            number: number,
            media:putMedia
        }

        await axios.put('http://localhost:8080/table-category/update/', putTableCategory,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({tableCategoryList: this.state.tableCategoryList})
            });
        this.setState({loadingIsVisible: true});

    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    filterList = async (e) => {
        this.setState({loadingIsVisible: true});
        await this.setState({
            tableCategoryList: this.state.tableCategoryList.filter(
                productByFilter => productByFilter.name == e
            )
        })
        this.setState({loadingIsVisible: false});
    }

    onClickMediaItem = (e) => {
        console.log(e)
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID: e.id,
            selectedMediaURL:e.fileContent
        })
    }

    render() {
        const {tableCategoryList, isUpdate, id, name, number,selectedMediaName,mediaList} = this.state
        return (
            <div>
                <Header/>
                <Link to="/addtablecategory">
                    <button className="btn btn-success addTableCategoryButton">+ Add Table Category</button>
                </Link>
                {
                    isUpdate ?
                        <div className="col-md-12 mb-4 mt-2">
                            <div className="card" align="left">
                                <div className="card-header" align="center">
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
                                                   disabled={id}
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

                                        <div className="form-group">
                                            <label htmlFor="tablecategorynumber">Table Category Number</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder={number}
                                                   name="number"
                                                   id="tablecategorynumber"
                                                   value={number}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="dropdown d-inline">
                                            <label htmlFor="price">Table Category Media : </label>
                                            <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="true">
                                                {selectedMediaName}
                                            </button>
                                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                {
                                                    mediaList.map(v => {
                                                        return (
                                                            <div className="row col-md -12">
                                                                <a className="dropdown-item"
                                                                   onClick={this.onClickMediaItem.bind(this, v)}>
                                                                    {v.name}
                                                                </a>
                                                            </div>
                                                        )
                                                    })
                                                }
                                            </div>
                                        </div>

                                        <button className="btn btn-warning btn-block mt-3"
                                                onClick={this.updateTableCategory}>Update
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div> : null
                }
                <Table striped bordered hover className="usersTable" id="table-catogory-to-xls">
                    <thead>
                    <tr>
                        <th>Table Category ID</th>
                        <th>Table Category Name</th>
                        <th>Table Number</th>
                        <th>Table Media</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        tableCategoryList.map(v => {
                            return (
                                <tr align="center">
                                    <td>{v.id}</td>
                                    <td>
                                        <button className="btn btn-link"
                                                onClick={() => this.filterList(v.name)}>{v.name}</button>
                                    </td>
                                    <td>
                                        {v.number}
                                    </td>
                                    <td align="center">
                                        <img src={'data:image/png;base64,' + v.media.fileContent} width="100"
                                             style={{margin: 10}}/>
                                    </td>
                                    <td>
                                        <button className="btn btn-warning mr-2"
                                                onClick={this.onClickUpdateTableCategoryBtn.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger mr-2"
                                                onClick={this.onClickDeleteTableCategoryBtn.bind(this, v)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>
                <ReactToExcel
                    className="btn btn-success"
                    table="table-catogory-to-xls"
                    filename="tableCategoryExcelFile"
                    sheet="sheet 1"
                    buttonText="Export"
                />
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default TableCategoryList;