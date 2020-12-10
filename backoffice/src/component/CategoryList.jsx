import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import Header from "./Header";
import {Link} from "react-router-dom";
import axios from "axios";

class CategoryList extends Component {
    state = {
        categoryList: [],
        isUpdate: false,
        id: "",
        name: "",
        description: "",
        imageToUrl: ""
    }

    componentDidMount() {
        const {categoryList} = this.state

        let uri = "http://localhost:8080/category/list-category";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    categoryList: data
                })
            })
    }

    onClickDeleteBtn = (e) => {
        window.location.reload();
        axios.delete('http://localhost:8080/category/delete-category/' + e.id,
            {headers: {Authorization: sessionStorage.getItem('token')}});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickUpdateBtn = (e) => {
        this.setState({
            isUpdate: !this.state.isUpdate,
            id: e.id,
            name: e.name,
            description: e.description,
            imageToUrl: e.imageToUrl
        })
    }

    categoryUpdate = () => {
        const {id,name, description, imageToUrl} = this.state;

        const putCategory = {
            id:id,
            name:name,
            description: description,
            imageToUrl: imageToUrl
        }

        axios.put('http://localhost:8080/category/update-category',putCategory,
            {headers:{Authorization: sessionStorage.getItem('token')}});
    }

    render() {
        const {categoryList, isUpdate, id, name, description, imageToUrl} = this.state;
        return (
            <div>
                <Header></Header>
                <Link to="/addcategory">
                    <button className="btn btn-success addCategoryButton">+ Add Category</button>
                </Link>
                {
                    isUpdate ?
                        <div>
                            <div className="col-md-12 mb-4 mt-2">
                                <div className="card" align="left">
                                    <div className="card-header" align="center">
                                        <h4>Update Category</h4>
                                    </div>
                                    <div className="card-body">
                                        <form>
                                            <div className="form-group">
                                                <label htmlFor="id">Category ID</label>
                                                <input type="number"
                                                       className="form-control"
                                                       placeholder={id}
                                                       name="id"
                                                       id="idInput"
                                                       value={id}
                                                       disabled={id}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="name">Category Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={name}
                                                       name="name"
                                                       id="nameInput"
                                                       value={name}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="description">Category Description</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={description}
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
                                                       placeholder={imageToUrl}
                                                       name="imageToUrl"
                                                       id="iamgeInput"
                                                       value={imageToUrl}
                                                       onChange={this.changeInput}
                                                />
                                            </div>
                                            <button className="btn btn-warning btn-block"
                                                    onClick={this.categoryUpdate}>Update
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div> : null
                }
                <Table striped bordered hover className="usersTable" >
                    <thead>
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                        <th>Category Description</th>
                        <th>Category ImageUrl</th>
                        <td>Category Image</td>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        categoryList.map(v => {
                            return (
                                <tr align="center">
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td>{v.description}</td>
                                    <td>{v.imageToUrl}</td>
                                    <td>
                                        <img src={'data:image/png;base64,' + v.media.fileContent} width="100" style={{margin:10}}/>
                                    </td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2"
                                                onClick={this.onClickUpdateBtn.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={this.onClickDeleteBtn.bind(this, v)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default CategoryList;