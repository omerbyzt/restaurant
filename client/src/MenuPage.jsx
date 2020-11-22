import React, {Component} from 'react';
import './App.css';
import {Link} from "react-router-dom";

class MenuPage extends Component {
    render() {
        return (
            <div>
                <div>
                    <button className="btn btn-info backButton">Back</button>
                </div>
                <Link to="/home"><button className="btn btn-success menuBtn"><h1>SHOPPING</h1></button></Link>
                <button className="btn btn-success menuBtn">Card 2</button>
                <button className="btn btn-success menuBtn">Card 3</button>
                <button className="btn btn-success menuBtn">Card 4</button>
                <button className="btn btn-success menuBtn">Card 5</button>
                <button className="btn btn-success menuBtn">Card 6</button>
                <button className="btn btn-success menuBtn">Card 7</button>
                <button className="btn btn-success menuBtn">Card 8</button>
                <button className="btn btn-success menuBtn"><h1>EXIT</h1></button>
            </div>
        );
    }
}

export default MenuPage;