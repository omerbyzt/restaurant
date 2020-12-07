import React, {Component} from 'react';
import {Link} from "react-router-dom";
import '../App.css';
import UserLogin from './UserLogin'

class Header extends Component {

    clickSignOut = (e) => {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");
    }

    render() {
        return (
            <div className="headerCss">
                <Link to="/home">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Product</button>
                </Link>
                <Link to="/userlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">User</button>
                </Link>
                <Link to="/authlist">
                    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Auth</button>
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

                {/*<Link to="/tablelist">*/}
                {/*    <button className="btn btn-info btn-lg mb-2 mt-2 ml-2 btnCss">Table</button>*/}
                {/*</Link>*/}

                <Link to="/">
                    <button className="btn btn-danger btn-lg signOutBtn" onClick={this.clickSignOut}>Sign Out
                        : {sessionStorage.getItem('username')}</button>
                </Link>

            </div>
        );
    }
}

export default Header;