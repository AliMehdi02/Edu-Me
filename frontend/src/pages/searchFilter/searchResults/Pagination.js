import './styles/pagination.css';

const Pagination = ({postsPerPage, totalPosts, paginate}) => {
    const pageNumbers = [];

    for (let i=1; i<=Math.ceil(totalPosts / postsPerPage); i++) {
        pageNumbers.push(i);
    }

    return (
        <nav>
            <ul className="search-pagination">
                {pageNumbers.map(number => (
                    <li key={number} className="search-pagination-page-item">
                        <p onClick={(event) => {
                            paginate(number);
                            // console.log(document.getElementsByClassName('search-pagination-page-item'));
                            [...document.getElementsByClassName('search-pagination-page-item')].forEach((element) => {
                                element.style = 'background: none;';
                            });
                            event.target.parentElement.style = 'background: rgb(155, 87, 218);';
                        }} className="search-pagination-page-link">
                            {number}
                        </p>
                    </li>
                ))}
            </ul>
        </nav>
    );
};

export default Pagination;