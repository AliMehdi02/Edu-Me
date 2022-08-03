import React, { useState, useEffect } from 'react';
import Posts from './Posts';
import Pagination from './Pagination';
import './styles/search-results.css';
import { useSelector, useDispatch } from 'react-redux';
import {toggleSearchLoadingAction, isSearchPostAction} from './../../../redux/actions';
import Navbarr from '../../Navbar/Indexx';

const SearchResults = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(4);

  const toggleSearchLoading = useSelector(state => state.toggleSearchLoading);
  const isSearchPost = useSelector(state => state.isSearchPost);
  const dispatch = useDispatch();

    useEffect(async () => {
      const urlBreakdown = window.location.href.split('?');
      // setLoading(true);
      dispatch(toggleSearchLoadingAction());

      dispatch(isSearchPostAction(true));

      const token = "Bearer " + localStorage.getItem("token");

      const res = await fetch("http://localhost:8080/search/search-filter?"
      + urlBreakdown[1], {
        method: "GET",
        headers: {
          "Content-type": "application/json",
          'Authorization': token
        },
      }).catch(err => {
        console.error(err)
      }).then((res) => res.json())
        .then((json) => setPosts(json));

      // setLoading(false);
      dispatch(toggleSearchLoadingAction());
    }, []);


  // Get current posts
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = posts.slice(indexOfFirstPost, indexOfLastPost);

  // Change page
  const paginate = pageNumber => setCurrentPage(pageNumber);

  return (
    <div>
      <Navbarr/>
    <div className="search-results-parent-container">
        <div className='search-results-container'>
            <div>
                <h2 className='search-results-page-title'>Search Results</h2>
                <Posts posts={currentPosts}/>
            </div>
        <Pagination postsPerPage={postsPerPage} totalPosts={posts.length} paginate={paginate}/>
        </div>
    </div>
    </div>
  );
};

export default SearchResults;
