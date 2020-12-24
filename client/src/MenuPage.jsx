import React, {Component} from 'react';
import './App.css';
import {Link} from "react-router-dom";
import UserContext from "./Context";
import Loading from "./Loading";
import {Modal} from "react-bootstrap";
import axios from 'axios';

class MenuPage extends Component {
    static contextType = UserContext;
    state = {
        loadingIsVisible: false,
        addOrListCustomerModal: false,
        selectCustomerModal: false,
        name: "",
        surname: "",
        phoneNumber: "",
        address: "",
        customerList: [],
        pageCount: 0,
        scrollPosition: 0,
        last: false
    }

    constructor(props) {
        super(props);
        this.myRef = React.createRef();
    }iişö

    async componentDidMount() {

        const {token, setToken, setUserName} = this.context;

        if (localStorage.getItem("token") !== null) {
            setToken(localStorage.getItem("token"));
            setUserName(localStorage.getItem("username"))

            await axios.get('http://localhost:8080/customer/slice?page=' + this.state.pageCount,
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({customerList: res.data.content, last: res.data.last})
                });

        } else {
            if (token !== "No Token") {
                //remember me olmadan giriş
            } else {
                this.props.history.push('/');
            }
        }
    }

    clickSignOutButton = () => {
        this.setState({loadingIsVisible: true})
        const {setToken, setUserName} = this.context;
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("username");
        setToken("No Token");
        setUserName("No User");
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        this.setState({loadingIsVisible: false})
    }

    customerModal = () => {
        this.setState({selectCustomerModal: !this.state.selectCustomerModal})
    }

    handleAddCustomerModal = () => {
        this.setState({
            addOrListCustomerModal: !this.state.addOrListCustomerModal,
            selectCustomerModal: false
        })
    }

    handleListCustomerModal = () => {
        this.setState({
            selectCustomerModal: !this.state.selectCustomerModal
        })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCustomer = async () => {
        const {name, surname, phoneNumber, address} = this.state;
        const token = "Basic YWRtaW46MTIz";
        const newCustomer = {
            name: name,
            surname: surname,
            phoneNumber: phoneNumber,
            address: address
        }

        axios.post("http://localhost:8080/customer", newCustomer, {
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        })
        sessionStorage.setItem("customer",name);
        this.setState({addOrListCustomerModal: false})
        this.props.history.push("/home");
    }

    onScroll = async () => {
        const {scrollPosition} = this.state;
        const scrollTop = this.myRef.current.scrollTop;
        this.setState({scrollPosition: scrollTop})
        console.log(this.state.customerList.length)

        if ((scrollPosition > (this.state.pageCount+1) * 330) && !this.state.last){
            this.state.pageCount++;
            await this.loadMoreCustomer();
            this.setState({scrollPosition: 0});
            this.myRef.current.scrollTop = (this.state.pageCount + 1) * 400;
        }
    }

    loadMoreCustomer = async () => {
        const {token} = this.context;
        await axios.get('http://localhost:8080/customer/slice?page=' + this.state.pageCount,
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({
                    customerList: [...this.state.customerList, ...res.data.content],
                    last: res.data.last
                })
            });
    }

    clickCutomer = (e) => {
        sessionStorage.setItem("customer",e.id);
        this.props.history.push("/home")
    }

    render() {
        const {username} = this.context;
        const {name, surname, phoneNumber, address, customerList} = this.state;
        return (
            <div>
                <Modal show={this.state.selectCustomerModal} onHide={() => this.handleListCustomerModal()}>
                    <Modal.Header closeButton>
                        Modal Header
                    </Modal.Header>
                    <Modal.Body>
                        <div className="customerModalBody" ref={this.myRef} onScroll={this.onScroll}>
                            {
                                customerList.map(v => {
                                    return (
                                        <button
                                            className="btn btn-outline-info btn-block customerButton" onClick={()=> this.clickCutomer(v)}>{v.name} {v.surname}</button>
                                    )
                                })
                            }
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-info" onClick={() => this.handleAddCustomerModal()}>Add Customers
                        </button>
                        <button className="btn btn-danger" onClick={() => this.handleListCustomerModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>
                <Modal show={this.state.addOrListCustomerModal} onHide={() => this.handleAddCustomerModal()}>
                    <Modal.Header closeButton>
                        Modal Header
                    </Modal.Header>
                    <Modal.Body>
                        {

                            <div className="col-md-12 mr-auto mb-4 mt-4">
                                <div className="card">
                                    <div className="card-header">
                                        <h4>Add Customer</h4>
                                    </div>
                                    <div className="card-body">
                                        <form>
                                            <div className="form-group">
                                                <label htmlFor="custName">Customer Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder="Enter Customer Name"
                                                       name="name"
                                                       id="custName"
                                                       value={name}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="custSurname">Customer Surname</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder="Enter Customer Surname"
                                                       name="surname"
                                                       id="custSurname"
                                                       value={surname}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="custPhoneNumber">Customer Phone Number</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder="Enter Customer Phone Number"
                                                       name="phoneNumber"
                                                       id="custPhoneNumber"
                                                       value={phoneNumber}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="custAddress">Customer Address</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder="Enter Customer Surname"
                                                       name="address"
                                                       id="custAddress"
                                                       value={address}
                                                       onChange={this.changeInput}
                                                />
                                            </div>
                                        </form>
                                        <button className="btn btn-warning btn-block"
                                                onClick={() => this.addCustomer()}>Add Customer
                                        </button>
                                    </div>
                                </div>
                            </div>

                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() => this.handleAddCustomerModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>
                <button className="btn btn-success menuBtn" onClick={() => this.customerModal()}><h1>Shopping</h1>
                </button>
                <Link to="/tablepage">
                    <button className="btn btn-success menuBtn"><h1>Tables</h1></button>
                </Link>
                <Link to="loading">
                    <button className="btn btn-success menuBtn">Loading</button>
                </Link>
                <button className="btn btn-success menuBtn">Card 4</button>
                <button className="btn btn-success menuBtn">Card 5</button>
                <button className="btn btn-success menuBtn">Card 6</button>
                <button className="btn btn-success menuBtn">Card 7</button>
                <button className="btn btn-success menuBtn">Card 8</button>
                <Link to="/">
                    <button className="btn btn-danger menuBtn" onClick={this.clickSignOutButton}><h1>Sign Out
                        : {username}</h1></button>
                </Link>

                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }

            </div>
        );
    }
}

export default MenuPage;