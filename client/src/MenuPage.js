import React, {Component} from 'react';
import './App.css';

class MenuPage extends Component {
    render() {
        return (
            <div className = "menuPage">
                <div>
                    <button className="btn btn-info backButton">Back</button>
                </div>
                <button className="btn btn-success menuBtn">Card 1</button>
                <button className="btn btn-success menuBtn">Card 2</button>
                <button className="btn btn-success menuBtn">Card 3</button>
                <button className="btn btn-success menuBtn">Card 4</button>
                <button className="btn btn-success menuBtn">Card 5</button>
                <button className="btn btn-success menuBtn">Card 6</button>
                <button className="btn btn-success menuBtn">Card 7</button>
                <button className="btn btn-success menuBtn">Card 8</button>
                <button className="btn btn-success menuBtn">Card 9</button>
            </div>
        );
    }
}

export default MenuPage;