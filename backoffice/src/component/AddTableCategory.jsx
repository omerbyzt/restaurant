import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";

class AddTableCategory extends Component {
    state = {
        name:""
    }

    addTableCategory = () => {
        const{name} = this.state

        const newTableCategory = {
            name:name
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

        const{id,name} = this.state

        return (
            <div>
                <Header/>

                <div className="col-md-12 mb-4 mt-4">
                    <div className = "card">
                        <div className = "card-header">
                            <h4>Add Table Category</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addTableCategory}>
                                <div className="form-group">
                                    <label htmlFor="name">Table Name</label>
                                    <input type="name"
                                           className="form-control"
                                           placeholder="Enter Table Name"
                                           name="name"
                                           id="nameInput"
                                           value={name}
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