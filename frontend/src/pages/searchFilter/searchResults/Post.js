import './styles/post.css';
import {useSelector} from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import ConfirmDeletion from '../../guides/user-guides/ConfirmDeletion';

const Post = ({postData}) => {
    const [delPost, setDelPost] = useState(false);

    const isSearchPost = useSelector(state => state.isSearchPost);

    const navigate = useNavigate();

    const handleEdit = () => {
        navigate('/edit-guide', {state: postData[0]});
    };

    const handleDelete = () => {
        setDelPost(true);
    };

    async function confirmDelete() {
        const uId= localStorage.getItem('UsersID from user table');
        const token = 'Bearer ' + localStorage.getItem('token');

        await fetch(`http://localhost:8080/delete-guide/${uId}/${postData[0]}`, {
            method: 'DELETE',
            headers: {
                'Authorization': token
            }
        });
        window.location.href = window.location.href;
    }

    const viewPost = async () => {
        const token = 'Bearer ' + localStorage.getItem('token');
        const res = await fetch(`http://localhost:8080/view-guides/${postData[0]}`, {
            method: 'GET',
            headers: {
                'Authorization': token
            }
        }).then(resp => resp.json());
        navigate('/view-guide', {state:res});
    };

    return (
        <>
        <ConfirmDeletion display={delPost} setDisplay={setDelPost} confirmDelete={confirmDelete}/>
        <div className="post-container">
            <h3 className="search-post-title" onClick={() => viewPost()}>{postData[1]}</h3>
            <span className="creation-data">
                {
                    (isSearchPost)?
                        <p className="creator-name">Created by: {postData[2]}</p>
                        :
                        <p className="creator-name">Created on: {postData[2]}</p>
                }
                <p className="last-update">Last updated: {postData[3]}</p>
            </span>
            <p className="desc">{postData[4]}</p>
            <span className="skill-level-data">
                <p className="search-results-topic">{postData[5]}</p>
                <p className="search-results-exp-lvl">{postData[6]}</p>
            </span>
            {(isSearchPost)?null:
                <div className='my-guide-control-btns'>
                    <button className='ctrl-guide-btn edit-guide-btn' onClick={() => handleEdit()}>Edit Guide</button>
                    <button className='ctrl-guide-btn del-guide-btn' onClick={() => handleDelete()}>Delete Guide</button>
                </div>
            }
        </div>
        </>
    );
};

export default Post;
