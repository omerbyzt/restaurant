import React, {Component} from 'react';
import {Link} from "react-router-dom";
import '../App.css';
import UserLogin from './UserLogin'
import UserContext from "../Context";

class Header extends Component {
    static contextType = UserContext;

    clickSignOut = (e) => {
        const {setToken,setUserName}=this.context;
        setUserName("No User")
        setToken("No Token")
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");

        localStorage.removeItem("token");
        localStorage.removeItem("username");
    }

    render() {
        const{username}=this.context;
        return (
            <div className="headerCss">
                <Link to="/home">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Product</button>
                </Link>
                <Link to="/userlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">User</button>
                </Link>
                <Link to="/authlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Role</button>
                </Link>
                <Link to="/orderlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Order</button>
                </Link>
                <Link to="/categorylist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Category</button>
                </Link>
                <Link to="/infolist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Info</button>
                </Link>
                <Link to="/tablecategorylist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Table Category</button>
                </Link>
                <Link to="/waiterlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Waiter</button>
                </Link>
                <Link to="/addimage">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Image</button>
                </Link>
                {/*<Link to="/addrole">*/}
                {/*    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Add Role</button>*/}
                {/*</Link>*/}


                {/*<Link to="/tablelist">*/}
                {/*    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Table</button>*/}
                {/*</Link>*/}

                <Link to="/">
                    <button className="btn btn-danger btn-lg signOutBtn" onClick={this.clickSignOut}>Sign Out
                        {/*: {sessionStorage.getItem('username')}</button>*/}
                        : {username}</button>
                </Link>

            </div>
        );
    }
}

export default Header;