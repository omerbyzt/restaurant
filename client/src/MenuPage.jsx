import React, {Component} from 'react';
import './App.css';
import {Link} from "react-router-dom";

class MenuPage extends Component {
    clickSignOutButton = () => {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");
    }
    render() {
        return (
            <div>
                <Link to="/home"><button className="btn btn-success menuBtn"><h1>SHOPPING</h1></button></Link>
                <button className="btn btn-success menuBtn">Card 2</button>
                <button className="btn btn-success menuBtn">Card 3</button>
                <button className="btn btn-success menuBtn">Card 4</button>
                <button className="btn btn-success menuBtn">Card 5</button>
                <button className="btn btn-success menuBtn">Card 6</button>
                <button className="btn btn-success menuBtn">Card 7</button>
                <button className="btn btn-success menuBtn">Card 8</button>
                <Link to="/">
                    <button className="btn btn-danger menuBtn" onClick={this.clickSignOutButton}><h1>Sign Out : {sessionStorage.getItem('username')}</h1></button>
                </Link>

            </div>
        );
    }
}

export default MenuPage;