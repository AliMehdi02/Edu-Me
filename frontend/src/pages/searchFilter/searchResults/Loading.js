import './styles/loading.css';

const Loading = () => {
  return (
      <div className='search-results-loading-container'>
          <div className="search-results-ring"></div>
          <span className="search-results-loading-text">loading...</span>
      </div>
  );
};

export default Loading;
