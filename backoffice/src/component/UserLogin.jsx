import React, {Component} from 'react';
import '../App.css';
import axios from "axios";
import UserContext from "../Context";
import Loading from "./Loading";

class LoginPage extends Component {
    static contextType = UserContext;
    state = {
        username: "",
        password: "",
        userList: [],
        isChecked:true,
        loadingIsVisible:false
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

        const{setUserName,setToken} = this.context;
        if(localStorage.getItem("token") !== null){
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));
            this.props.history.push('/home');
        }
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    login = async (value) => {
        const {setToken, setUserName} = this.context;
        this.setState({loadingIsVisible: true})
        await axios.get('http://localhost:8080/user/list',
            {headers: {Authorization: 'Basic ' + btoa(this.state.username + ":" + this.state.password)}})
            .then((res) => {

                setToken('Basic ' + btoa(this.state.username + ":" + this.state.password));
                setUserName(this.state.username);



                sessionStorage.setItem("token", 'Basic ' + btoa(this.state.username + ":" + this.state.password));
                sessionStorage.setItem("username", this.state.username);
                this.props.history.push('/home');

                // setToken('Basic ' + btoa(this.state.username + ":" + this.state.password));
                // setUserName(this.state.username);
                // this.props.history.push('/menu');
            }).catch(() => {
                this.state.username = "";
                this.state.password = "";
                this.props.history.push('/');
                window.alert("USERNAME OR PASSWORD WRONG!")

            });

        if (this.state.isChecked) {
            localStorage.setItem("token", 'Basic ' + btoa(this.state.username + ":" + this.state.password));
            localStorage.setItem("username", this.state.username);
        }
        this.setState({loadingIsVisible: false})
        value.preventDefault();
        //
        // this.props.history.push('/home');
        // sessionStorage.setItem("token", 'Basic ' + btoa("admin:123"));
        // sessionStorage.setItem("username", this.state.username);
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
                            <h1 className="loginPageTitle">Backoffice Side Login Page</h1>
                        </div>
                        <div className="card-body">
                            <div>
                                <form>
                                    <div className="form-group">
                                        <label htmlFor="nameInput">User Name</label>
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
                                        <label htmlFor="passwordInput">User Password</label>
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
                                        <input type="checkbox" className="custom-control-input" id="rememberme"
                                               checked={isChecked} onChange={this.toggleChange}/>
                                        <label className="custom-control-label" htmlFor="rememberme">Remember Me</label>
                                    </div>


                                </form>
                                <button className="btn btn-info btn-block mt-3" onClick={this.login}>Login</button>
                            </div>
                        </div>
                    </div>

                </div>
                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }
            </div>
        );
    }
}

export default LoginPage;