import React, {Component} from 'react';
import {Link} from "react-router-dom";
import '../App.css';
import UserLogin from './UserLogin'

class Header extends Component {

    clickSignOut  = (e) => {
            sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");
    }

    render() {
        return (
            <div>
                <Link to ="/home"><button className="btn btn-info btn-lg mb-2 mt-2 ml-2">Product List</button></Link>
                <Link to="/addproduct"><button className="btn btn-info btn-lg mb-2 mt-2 ml-2" >Add New Product</button></Link>
                <Link to ="/adduser"><button className="btn btn-info btn-lg mb-2 mt-2 ml-2" >Add New User</button></Link>
                <Link to="/userlist"><button className="btn btn-info btn-lg mb-2 mt-2 ml-2" >User List</button></Link>
                <Link to="/orderlist"><button className="btn btn-info btn-lg mb-2 mt-2 ml-2" >Order List</button></Link>
                <Link to="/"><button className = "btn btn-danger btn-lg signOutBtn" onClick={this.clickSignOut}>Sign Out : {sessionStorage.getItem('username')}</button></Link>
            </div>
        );
    }
}

export default Header;