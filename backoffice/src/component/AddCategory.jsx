import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";

class AddCategory extends Component {
    state = {
        name:"",
        description:"",
        imageToUrl:""
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCategory = () => {
        const {name, description, imageToUrl} = this.state;

        const newMedia = {
            id:1
        }

        const newCategory = {
            name : name,
            description: description,
            imageToUrl: imageToUrl,
            products:[],
            media:newMedia
        }

        axios.post("http://localhost:8080/category/add-category", newCategory,
            {headers:{Authorization:sessionStorage.getItem('token')}});
    }

    render() {
        const{name,description,imageToUrl}=this.state;
        return (
            <div>
                <Header/>
                <div className ="col-md-5 mr-auto mb-4 mt-4">
                    <div className="card">
                        <div className="card-header">
                            <h4>Add Category</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addCategory}>
                                <div className="form-group">
                                    <label htmlFor="name">Category Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Category Name"
                                           name="name"
                                           id="nameInput"
                                           value={name}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="description">Description Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Description Name"
                                           name="description"
                                           id="descInput"
                                           value={description}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="imageToUrl">Category Image Url</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Image Url"
                                           name="imageToUrl"
                                           id="descInput"
                                           value={imageToUrl}
                                           onChange={this.changeInput}
                                    />
                                </div>
                                <button className="btn btn-warning btn-block" type="submit">Add Category</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddCategory;