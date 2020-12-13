import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import axios from "axios";
import {Link} from "react-router-dom";
import UserContext from "../Context";
import Loading from "./Loading";

class AuthList extends Component {
    static contextType = UserContext;
    state = {
        authUpdate : false,
        authList:[],
        name:"",
        authority:"",
        id:"",
        loadingIsVisible:false
    }
    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/role/list";
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
                        authList: data
                    })
                })
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickedUpdateAuthBtn = (e) => {
        this.setState({
            authUpdate: !this.state.authUpdate,
            name: e.name,
            id:e.id
        })
    }

    updateAuth = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {name, id} = this.state;

        const putAuth = {
            id: id,
            name: name
        }

        await axios.put('http://localhost:8080/role/update/', putAuth,
            // {headers:{Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
        this.setState({loadingIsVisible: true});
    }

    deleteRole = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        await axios.delete('http://localhost:8080/role/delete/' + e.id,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({authList: this.state.authList.filter(table => table.id !== e.id)})
            });
        this.setState({loadingIsVisible: true});
    }

    render() {
        const{authUpdate,authList,name,id} = this.state;
        return (
            <div>
                <Header/>
                <Link to = "/addrole"><button className="btn btn-success productListAddProduct">+ Add Role</button></Link>
                {
                    authUpdate ?
                        <div>
                            <div className="col-md-12 mb-4 mt-2">
                                <div className="card" align="left">
                                    <div className="card-header" align="center">
                                        <h4>Update Auth</h4>
                                    </div>
                                    <div className="card-body">
                                        <form>

                                            <div className="form-group">
                                                <label htmlFor="idInput">Role ID</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={id}
                                                       name="id"
                                                       id="idInput"
                                                       value={id}
                                                       disabled = {id}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="nameInput">Role Name</label>
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
                                                    onClick={this.updateAuth}>Update
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>:null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                        <tr>
                            <th>#ID</th>
                            <th>Name</th>
                            <th>Buttons</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        authList.map(v => {
                            return(
                                <tr>
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2" onClick={this.onClickedUpdateAuthBtn.bind(this,v)}>Update</button>
                                        <button className="btn btn-danger mr-2" onClick={this.deleteRole.bind(this,v)}>Delete</button>
                                    </td>
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

export default AuthList;