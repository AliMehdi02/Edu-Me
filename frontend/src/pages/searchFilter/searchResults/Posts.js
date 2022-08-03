import Post from './Post';
import './styles/posts.css';
import Loading from './Loading';
import { useSelector } from 'react-redux';

const Posts = ({posts}) => {
    const toggleSearchLoading = useSelector(state => state.toggleSearchLoading);
    const isSearchPost = useSelector(state => state.isSearchPost);

    if (toggleSearchLoading) {
        return <Loading/>;
    }


    return (
        <>
            {(posts.length === 0)?
                (isSearchPost)?
                    <p className='no-search-results'>No results matched the search criteria.</p>:
                    <p className='no-search-results'>You have not posted any guides.</p>
                :
                <ul className="search-result-post">
                    {posts.map((post) => (
                        <li key={parseInt(post[0])} className="">
                            <Post postData={post}/>
                        </li>
                    ))}
                </ul>
            }
        </>
    );
};

export default Posts;
