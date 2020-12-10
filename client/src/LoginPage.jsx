import React, {Component} from 'react';
import './App.css';
import axios from "axios";
import UserContext from "./Context";

class LoginPage extends Component {
    static contextType = UserContext;

    state = {
        username: "",
        password: "",
        userList: "",
        isChecked:false
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    componentDidMount() {
        const{setUserName,setToken} = this.context;
        if(localStorage.getItem("token") !== null){
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));
            this.props.history.push('/menu');
        }else{
            axios.get('http://localhost:8080/users/listall',
                {headers: {Authorization: 'Basic ' + btoa('admin:pass3')}})
                .then((res) => {
                    this.setState({userList: res.data});
                    console.log(res.data)
                });

            const{setTable} = this.context;
            setTable("No Table");
            // sessionStorage.setItem("table", "No Table");
        }
    }

    clickLoginButton = () => {
        const{setToken,setUserName} = this.context;

        if (this.state.userList.filter(user => (user.username === this.state.username) && (user.password.substring(6, user.password.size) === this.state.password)).length > 0) {
            //Session
            // sessionStorage.setItem("token", 'Basic ' + btoa(this.state.username + ":" + this.state.password));
            // sessionStorage.setItem("username", this.state.username);
            //Context
            setToken('Basic ' + btoa(this.state.username + ":" + this.state.password));

            setUserName(this.state.username);

            this.props.history.push('/menu');
        } else {
            this.props.history.push('/');
            window.alert("USERNAME OR PASSWORD WRONG!")
        }

        if(this.state.isChecked){
            localStorage.setItem("token" , 'Basic ' + btoa(this.state.username + ":" + this.state.password));
            localStorage.setItem("username",this.state.username);
        }
    }

    toggleChange = () => {
        this.setState({
            isChecked: !this.state.isChecked,
        });
    }

    render() {
        const {username, password, isChecked} = this.state;

        return (
            <div className="cardCss">
                <div className="col-md-6 mb-4 mt-2">
                    <div className="card">
                        <div className="card-header">
                            <h1 className="loginPageTitle">Client Side Login Page</h1>
                        </div>
                        <div className="card-body">
                            <div>
                                <form onSubmit={this.login}>
                                    <div className="form-group">
                                        <label htmlFor="name">User Name</label>
                                        <input type="text"
                                               className="form-control"
                                               placeholder="Enter User Name"
                                               name="username"
                                               id="nameInput"
                                               value={username}
                                               onChange={this.changeInput}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="password">User Password</label>
                                        <input type="password"
                                               className="form-control"
                                               placeholder="Enter User Password"
                                               name="password"
                                               id="passwordInput"
                                               value={password}
                                               onChange={this.changeInput}
                                        />
                                    </div>

                                    <div className="custom-control custom-checkbox">
                                        <input type="checkbox" className="custom-control-input" id="rememberme" checked={isChecked} onChange={this.toggleChange}/>
                                        <label className="custom-control-label" for="rememberme">Remember Me</label>
                                    </div>

                                    <button className="btn btn-info btn-block mt-3"
                                            onClick={() => this.clickLoginButton()}>Login
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        );

    }
}

export default LoginPage;