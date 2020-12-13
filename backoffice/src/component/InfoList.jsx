import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import UserContext from "../Context";
import Loading from "./Loading";

class InfoList extends Component {
    static contextType = UserContext;
    state = {
        infoList: [],
        loadingIsVisible:false
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/infolist";

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
                        infoList: data
                    })
                })
        } else {
            this.props.history.push('/');
        }
        this.setState({loadingIsVisible: false});
    }

    render() {
        const {infoList} = this.state;
        return (
            <div>
                <Header/>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Key</th>
                        <th>Value</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        infoList.map(v => {
                            return (
                                <tr>
                                    <td>{v.key}</td>
                                    <td>{v.value}</td>
                                </tr>
                            )
                        })
                    }

                    </tbody>
                </Table>
                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }
            </div>
        );
    }
}

export default InfoList;