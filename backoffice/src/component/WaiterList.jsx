import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";
import axios from "axios";
import UserContext from "../Context";
import Loading from "./Loading";
import {Modal} from "react-bootstrap";

class WaiterList extends Component {
    static contextType = UserContext;
    state = {
        waiterList: [],
        isVisible: false,
        id: "",
        name: "",
        phoneNumber: "",
        mail: "",
        address: "",
        urlToImage: "",
        salary: "",
        loadingIsVisible: false,
        mediaDTO: "",
        mediaList: [],
        selectedMediaName: "Select Media",
        selectedMediaID: "",
        selectedMediaURL: "",
        showModal: false,
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/waiter/list-waiters";
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
                        waiterList: data
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

    updateWaiterOpenForm = (e) => {
        this.setState({
            isVisible: !this.state.isVisible,
            id: e.id,
            name: e.name,
            phoneNumber: e.phoneNumber,
            mail: e.mail,
            address: e.address,
            urlToImage: e.urlToImage,
            salary: e.salary,
        })
        console.log(e)
    }

    deleteWaiter = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        await axios.delete('http://localhost:8080/waiter/delete-waiter/' + e.id,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({waiterList: this.state.waiterList.filter(table => table.id !== e.id)})
            });
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    updateWaiter = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {id, name, phoneNumber, mail, address, urlToImage, salary, selectedMediaURL, selectedMediaName, selectedMediaID} = this.state;
        const newMedia = {
            id: selectedMediaID,
            name: selectedMediaName,
            fileContent: selectedMediaURL
        }
        console.log(newMedia)
        const putWaiter = {
            id: id,
            name: name,
            phoneNumber: phoneNumber,
            mail: mail,
            address: address,
            urlToImage: urlToImage,
            salary: salary,
            mediaDTO: newMedia
        }

        await axios.put('http://localhost:8080/waiter/update-waiter', putWaiter,
            // {headers:{Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({waiterList: this.state.waiterList})
            });
        this.props.history.push('/waiterlist');
        this.setState({loadingIsVisible: false});
    }

    closeUpdate = () => {
        this.setState({
            isVisible: !this.state.isVisible
        })
    }

    onClickItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaURL: e.fileContent,
            selectedMediaID: e.id
        })
        console.log(e)
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

    render() {
        const {waiterList, isVisible, id, name, phoneNumber, mail, address, urlToImage, salary, mediaList, selectedMediaName} = this.state;
        return (
            <div>
                <Header/>

                <Modal show={this.state.showModal} onHide={() => this.handleModal()}>
                    <Modal.Header closeButton><h4>{selectedMediaName}</h4></Modal.Header>
                    <Modal.Body align="center">
                        {
                            <img src={'data:image/png;base64,' + this.state.selectedMediaURL} width="250"
                                 style={{margin: 10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <Link to="/addwaiter">
                    <button className="btn btn-success productListAddProduct">+ Add Waiter</button>
                </Link>
                {
                    isVisible ?
                        <div className="col-md-6 mr-auto mb-4 mt-2">
                            <div className="card">
                                <div className="card-header">
                                    <h4>Update Waiter</h4>
                                    {/*<span className="cardHeader">Update Waiter</span>*/}
                                    {/*<button className="btn btn-danger closeButton" onClick={this.closeUpdate}>Close</button>*/}
                                </div>
                                <div className="card-body">
                                    <form className="d-inline">
                                        <div className="form-group">
                                            <label htmlFor="input-waiter-id">Waiter ID</label>
                                            <input type="number"
                                                   className="form-control"
                                                   name="name"
                                                   id="input-waiter-id"
                                                   value={id}
                                                   disabled={id}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-name">Waiter Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Name"
                                                   name="name"
                                                   id="input-waiter-name"
                                                   value={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-phoneNumber">Waiter Phone Number</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Phone Number"
                                                   name="phoneNumber"
                                                   id="input-waiter-phoneNumber"
                                                   value={phoneNumber}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-mail">Waiter Mail</label>
                                            <input type="mail"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Mail"
                                                   name="mail"
                                                   id="input-waiter-mail"
                                                   value={mail}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-address">Waiter Address</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Address"
                                                   name="address"
                                                   id="input-waiter-address"
                                                   value={address}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-urlToImage">Waiter Image URL</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Image URL"
                                                   name="urlToImage"
                                                   id="input-waiter-urlToImage"
                                                   value={urlToImage}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-salary">Waiter Salary</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Salary"
                                                   name="salary"
                                                   id="input-waiter-salary"
                                                   value={salary}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="dropdown d-inline">
                                            <label htmlFor="price">Waiter Media : </label>
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
                                                                   onClick={this.onClickItem.bind(this, v)}
                                                                >
                                                                    {v.name}
                                                                </a>
                                                            </div>

                                                        )
                                                    })
                                                }
                                            </div>
                                        </div>


                                    </form>
                                    <button className="btn btn-link ml-2 " onClick={() => this.showMedia()}>Show Media
                                    </button>
                                    <br/>
                                    <button className="btn btn-warning submitButton mt-3"
                                            onClick={this.updateWaiter}>Update Waiter
                                    </button>
                                    <button className="btn btn-danger bottomCloseButton mt-3"
                                            onClick={this.closeUpdate}>Cancel
                                    </button>
                                </div>
                            </div>
                        </div> : null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                    <tr>
                        <th>#Waiter ID</th>
                        <th>Waiter Name</th>
                        <th>Waiter Phone Number</th>
                        <th>Waiter Mail</th>
                        <th>Waiter Address</th>
                        <th>Waiter Image Url</th>
                        <th>Waiter Salary</th>
                        <th>Waiter Image</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        waiterList.map(v => {
                            return (
                                <tr>
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td>{v.phoneNumber}</td>
                                    <td>{v.mail}</td>
                                    <td>{v.address}</td>
                                    <td>{v.urlToImage}</td>
                                    <td>{v.salary}</td>
                                    <td align="center">
                                        <img src={'data:image/png;base64,' + v.mediaDTO.fileContent} width="100"
                                             style={{margin: 10}}/>
                                    </td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2"
                                                onClick={this.updateWaiterOpenForm.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger mr-2"
                                                onClick={this.deleteWaiter.bind(this, v)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default WaiterList;