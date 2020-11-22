import React, {Component} from 'react';
import '../App.css';
import axios from "axios";

class LoginPage extends Component {
    state = {
        userName: "",
        password: "",
        userList: [],
        trueUser : false
    }
    componentDidMount() {
        axios.get('http://localhost:8080/user/listall' ,
            {headers:{Authorization:'Basic '+btoa('admin:pass3')}})
            .then((res)=>{
                this.setState({userList: res.data});
                console.log(res.data)
            });



        console.log(this.state.userList);
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    login = (value) => {

        if((this.state.userList.filter(name => name.userName === this.state.userName).length>0) && (this.state.userList.filter(pass => pass.password === this.state.password).length>0)){
            sessionStorage.setItem("token",'Basic '+btoa(this.state.userName+":"+this.state.password));
            sessionStorage.setItem("username",this.state.userName);
            this.props.history.push('/home');
        }
        else{
            this.props.history.push('/');
            window.alert("USERNAME OR PASSWORD WRONG!")
        }
    }

    render() {
        const {userName, password} = this.state;
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
                                               name="userName"
                                               id="nameInput"
                                               value={userName}
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

                                    <button className="btn btn-info btn-block">Login</button>
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