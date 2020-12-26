import React, {Component} from 'react';
import axios from 'axios';
import Header from "./Header";
import UserContext from "../Context"
import Loading from "./Loading";
import {Modal} from "react-bootstrap";

class AddProduct extends Component {
    static contextType = UserContext;
    state = {
        isShowCard: true,
        id: "",
        name: "",
        desc: "",
        category: "",
        price: "",
        categoryList: [],
        btnCategoryName: "Select Category Name",
        selectedCategoryId: "",
        multiCategory: [],
        mediaList: [],
        selectedMediaID: "",
        selectedMediaName: "Select Media",
        selectedMediaURL: "",
        loadingIsVisible: false,
        showModal: false,
    }

    async componentDidMount() {
        const {token} = this.context
        this.setState({loadingIsVisible: true});
        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/category/list-category";

            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    // 'Authorization': sessionStorage.getItem('token'),
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        categoryList: data
                    })
                })

            await axios.get('http://localhost:8080/file/list',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({mediaList: res.data})
                });
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addProduct = async (e) => {
        this.setState({loadingIsVisible: true});
        const {name, desc, price, selectedCategoryId, btnCategoryName, selectedMediaID, selectedMediaName, selectedMediaURL} = this.state;
        const {token} = this.context
        const newMedia = {
            id: selectedMediaID,
            name: selectedMediaName,
            fileContent: selectedMediaURL
        }

        const newProduct = {
            productName: name,
            productDesc: desc,
            productCategory: btnCategoryName,
            productPrice: price,
            categoriesIds: this.state.multiCategory,
            mediaDTO: newMedia
        }
        // axios.post("http://localhost:8080/category/add-product/"+selectedCategoryId, newProduct,
        //     {headers:{Authorization: sessionStorage.getItem('token')}}
        //     );
        await axios.post("http://localhost:8080/product/add-product", newProduct,
            // {headers: {Authorization: sessionStorage.getItem('token')}}
            {headers: {Authorization: token}}
        );
        this.setState({loadingIsVisible: false});
    }

    onClickItem = (e) => {
        this.setState({
            btnCategoryName: e.name,
            selectedCategoryId: e.id,
        })
        this.state.multiCategory.push(e.id)
    }

    onClickMediaItem = (e) => {
        console.log(e)
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID: e.id,
            selectedMediaURL: e.fileContent
        })
    }
    handleModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    showMedia = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }
    onClickMediaItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaUrl: e.fileContent,
            selectedMediaID: e.id
        })
    }

    render() {
        const {name, desc, category, price, isShowCard, categoryList, btnCategoryName, mediaList, selectedMediaName} = this.state;
        return (
            <div>
                <Header/>

                <Modal show={this.state.showModal} onHide={() => this.handleModal()}>
                    <Modal.Header closeButton><h4>{selectedMediaName}</h4></Modal.Header>
                    <Modal.Body align="center">
                        {
                            <img src={'data:image/png;base64,' + this.state.selectedMediaUrl} width="250"
                                 style={{margin: 10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <div className="col-md-6 mr-auto mb-4 mt-4">
                    {
                        isShowCard ?
                            <div className="card" align="left">
                                <div className="card-header" align="center">
                                    <h4>Add Product</h4>
                                </div>
                                <div className="card-body">
                                    <form className="d-inline">
                                        <div className="form-group">
                                            <label htmlFor="name">Product Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Product Name"
                                                   name="name"
                                                   id="nameInput"
                                                   value={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="desc">Product Description</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Product Description"
                                                   name="desc"
                                                   id="descInput"
                                                   value={desc}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="price">Product Price</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Product Price"
                                                   name="price"
                                                   id="priceInput"
                                                   value={price}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="dropdown">
                                            <label htmlFor="price">Product Category : </label>
                                            <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="true">
                                                {btnCategoryName}
                                            </button>
                                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                {
                                                    categoryList.map(v => {
                                                        return (

                                                            <div className="row col-md -12">
                                                                <label>
                                                                    <input type="checkbox" value=""
                                                                           onClick={this.onClickItem.bind(this, v)}/>{v.name}
                                                                </label>
                                                            </div>

                                                        )
                                                    })
                                                }
                                            </div>
                                        </div>

                                        <div className="dropdown d-inline">
                                            <label htmlFor="price">Product Media : </label>
                                            <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="true">
                                                {selectedMediaName}
                                            </button>
                                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                {
                                                    mediaList.map(v => {
                                                        return (
                                                            <div className="row col-md -12">
                                                                <a className="dropdown-item"
                                                                   onClick={this.onClickMediaItem.bind(this, v)}>
                                                                    {v.name}
                                                                    {/*<img src={'data:image/png;base64,' + v.fileContent} width="25"*/}
                                                                </a>
                                                            </div>
                                                        )
                                                    })
                                                }
                                            </div>
                                        </div>

                                    </form>

                                    <button className="btn btn-link ml-2" onClick={() => this.showMedia()}>Show Media
                                    </button>
                                    <button className="btn btn-warning btn-block addProductButtonCss"
                                            onClick={this.addProduct}>Add Product
                                    </button>
                                </div>
                            </div> : null
                    }
                </div>

                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>

        );
    }

}

export default AddProduct;