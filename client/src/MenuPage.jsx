import React, {Component} from 'react';
import './App.css';
import {Link} from "react-router-dom";
import UserContext from "./Context";
import Loading from "./Loading";

class MenuPage extends Component {
    static contextType = UserContext;
    state = {
        loadingIsVisible:false
    }

    componentDidMount() {

        const {token, setToken, setUserName} = this.context;

        if (localStorage.getItem("token") !== null) {
            setToken(localStorage.getItem("token"));
            setUserName(localStorage.getItem("username"))
        } else {
            if (token !== "No Token") {
                //remember me olmadan giriş
            } else {
                this.props.history.push('/');
            }
        }

    }

    clickSignOutButton = () => {
        this.setState({loadingIsVisible:true})
        const {setToken , setUserName} = this.context;
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");
        setToken("No Token");
        setUserName("No User");
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        this.setState({loadingIsVisible:false})
    }

    render() {
        const {username} = this.context;
        return (
            <div>
                <Link to="/home"><button className="btn btn-success menuBtn"><h1>Shopping</h1></button></Link>
                <Link to ="/tablepage"><button className="btn btn-success menuBtn"><h1>Tables</h1></button></Link>
                <Link to = "loading"><button className="btn btn-success menuBtn">Loading</button></Link>
                <button className="btn btn-success menuBtn">Card 4</button>
                <button className="btn btn-success menuBtn">Card 5</button>
                <button className="btn btn-success menuBtn">Card 6</button>
                <button className="btn btn-success menuBtn">Card 7</button>
                <button className="btn btn-success menuBtn">Card 8</button>
                <Link to="/">
                    <button className="btn btn-danger menuBtn" onClick={this.clickSignOutButton}><h1>Sign Out : {username}</h1></button>
                </Link>

                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }

            </div>
        );
    }
}

export default MenuPage;