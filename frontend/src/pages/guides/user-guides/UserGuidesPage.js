import {useState, useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {isSearchPostAction, toggleSearchLoadingAction} from '../../../redux/actions';
import Posts from '../../searchFilter/searchResults/Posts';
import { idToTopic, idToExp } from '../../../utils/Utils';
import Navbarr from '../../Navbar/Indexx';
// import ConfirmDeletion from './ConfirmDeletion';

const UserGuidesPage = () => {
    const [posts, setPosts] = useState([]);
    
    const dispatch = useDispatch();

    function getCurrentlyLoggedInUser() {
        const userId = parseInt(localStorage.getItem("UsersID from user table"));
        console.log(userId);
        return userId;
    }

    useEffect(async () => {
        dispatch(toggleSearchLoadingAction());
        dispatch(isSearchPostAction(false));

        const token = 'Bearer ' + localStorage.getItem('token');
        const res = await fetch(`http://localhost:8080/user-posted-guides/${getCurrentlyLoggedInUser()}`, {
            method: 'GET',
            headers: {
                'Authorization': token
            }
        }).then(resp => resp.json());
        
        // modify response from backend into format expected by Posts and Post components
        const resPosts = res.map((post) => {
            return [post.id, post.title, post.dateCreated, post.lastUpdated, post.description, idToTopic(post.topicId), idToExp(post.expLvl)];
        });

        setPosts(resPosts);
        dispatch(toggleSearchLoadingAction());
    }, []);

    return (
        
        <div>
        <Navbarr/>

        <div className="search-results-parent-container">
            <div className="search-results-container">
                <div>
                    <h2 className="search-results-page-title">My Guides</h2>
                    <Posts posts={posts}/>
                    
                </div>
            </div>
        </div>
        </div>
    );
};

export default UserGuidesPage;
