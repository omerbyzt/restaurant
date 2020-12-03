import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";

class AddTableCategory extends Component {
    state = {
        name:"",
        number:""
    }

    addTableCategory = () => {
        const{name,number} = this.state

        const newTableCategory = {
            name:name,
            number:number
        }

        axios.post("http://localhost:8080/table-category/add", newTableCategory,
            {headers:{Authorization:sessionStorage.getItem('token')}});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    render() {

        const{id,name,number} = this.state

        return (
            <div>
                <Header/>

                <div className="col-md-6 mx-auto mb-4 mt-4">
                    <div className = "card">
                        <div className = "card-header">
                            <h4>Add Table Category</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addTableCategory}>
                                <div className="form-group">
                                    <label htmlFor="name">Table Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Table Name"
                                           name="name"
                                           id="nameInput"
                                           value={name}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="number">Table Number</label>
                                    <input type="number"
                                           className="form-control"
                                           placeholder="Enter Table Number"
                                           name="number"
                                           id="numberInput"
                                           value={number}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <button className="btn btn-warning btn-block " type="submit">Add Table Category</button>
                            </form>
                        </div>
                    </div>
                </div>
                
            </div>
        );
    }
}

export default AddTableCategory;