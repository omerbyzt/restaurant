import React, {Component} from 'react';
import Header from "./Header";
import Table from "react-bootstrap/Table";

class InfoList extends Component {
    state = {
        infoList:[]
    }
    componentDidMount() {
        const {infoList} = this.state

        let uri = "http://localhost:8080/infolist";

        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    infoList: data
                })
            })
    }

    render() {
        const{infoList} = this.state;
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
                            return(
                                <tr>
                                    <td>{v.key}</td>
                                    <td>{v.value}</td>
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

export default InfoList;