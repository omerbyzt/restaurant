import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import axios from "axios";

class AuthList extends Component {
    state = {
        authUpdate : false,
        authList:[],
        username:"",
        authority:"",
    }
    componentDidMount() {
        const{authList} = this.state

        let uri = "http://localhost:8080/auth/listall";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    authList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickedUpdateAuthBtn = (e) => {
        const{authUpdate,username,authority} = this.state;
        this.setState({
            authUpdate: !this.state.authUpdate,
            username: e.username,
            authority:e.authority
        })
    }

    updateAuth = () => {
        const {username,authority} = this.state;

        const putAuth = {
            username : username,
            authority: authority
        }

        axios.put('http://localhost:8080/auth/update/',putAuth,
            {headers:{Authorization: sessionStorage.getItem('token')}});
    }

    render() {
        const{authUpdate,authList,username,authority} = this.state;
        return (
            <div>
                <Header/>
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
                                                <label htmlFor="name">User Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={username}
                                                       name="username"
                                                       id="nameInput"
                                                       value={username}
                                                       disabled = {username}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="authority">Authority</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={authority}
                                                       name="authority"
                                                       id="authInput"
                                                       value={authority}
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
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>User Name</th>
                            <th>Auth</th>
                            <th>Buttons</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        authList.map(v => {
                            return(
                                <tr>
                                    <td>{v.username}</td>
                                    <td>{v.authority}</td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2" onClick={this.onClickedUpdateAuthBtn.bind(this,v)}>Update</button>
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

export default AuthList;