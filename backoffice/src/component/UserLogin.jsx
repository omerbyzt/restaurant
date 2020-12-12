import React, {Component} from 'react';
import '../App.css';
import axios from "axios";

class LoginPage extends Component {
    state = {
        username: "",
        password: "",
        userList: [],
        trueUser: false
    }

    componentDidMount() {

        // axios.get("http://localhost:8080/auth/loadadminauth");
        // axios.get("http://localhost:8080/users/loadadminusers");

        // axios.get('http://localhost:8080/users/listall' ,
        //     {headers:{Authorization:'Basic '+btoa('admin:pass3')}})
        //     .then((res)=>{
        //         this.setState({userList: res.data});
        //         console.log(res.data)
        //     });
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    login = (value) => {

        this.props.history.push('/home');
        sessionStorage.setItem("token",'Basic '+btoa("admin:123"));
        sessionStorage.setItem("username",this.state.username);
        // if(this.state.userList.filter(user => (user.username === this.state.username)&&(user.password.substring(6,user.password.size) === this.state.password)).length>0){
        //     sessionStorage.setItem("token",'Basic '+btoa(this.state.username+":"+this.state.password));
        //     sessionStorage.setItem("username",this.state.username);
        //     this.props.history.push('/home');
        // }
        // else{
        //     this.props.history.push('/');
        //     window.alert("USERNAME OR PASSWORD WRONG!")
        // }

        /*
        sessionStorage.setItem("token",'Basic '+btoa(this.state.username+":"+this.state.password));
        sessionStorage.setItem("username",this.state.userName);
        this.props.history.push('/home');

         */
    }

    render() {
        const {username, password} = this.state;
        return (
            <div className="cardCss">
                <div className="col-md-6 mb-4 mt-2">
                    <div className="card">
                        <div className="card-header">
                            <h1 className="loginPageTitle">Backoffice Side Login Page</h1>
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

                                    <button className="btn btn-info btn-block" type="submit">Login</button>
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