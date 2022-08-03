
const isSearchPostReducer = (state = false, action) => {
    switch (action.type) {
        case 'IS_SEARCH_POST':
            return action.payload;
        default:
            return state;
    }
};

export default isSearchPostReducer;
