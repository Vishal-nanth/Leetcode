class Solution {

    int[] seg;
    int MAXI = 50000;

    void update(int node,int l,int r,int idx,int val){
        if(l == r){
            seg[node] = val;
            return;
        }

        int mid = (l + r) / 2;

        if(idx <= mid){
            update(2 * node,l,mid,idx,val);
        }
        else{
            update(2 * node + 1,mid + 1,r,idx,val);
        }

        seg[node] = Math.max(seg[2 * node],seg[2 * node + 1]);
    }

    int query(int node,int l,int r,int ql,int qr){
        if(qr < l || ql > r)return 0;

        if(ql <= l && r <= qr){
            return seg[node];
        }

        int mid = (l + r) / 2;

        return Math.max(
            query(2 * node,l,mid,ql,qr),
            query(2 * node + 1,mid + 1,r,ql,qr)
        );
    }

    public List<Boolean> getResults(int[][] queries) {

        seg = new int[4 * (MAXI + 1)];

        TreeSet<Integer> barriers = new TreeSet<>();
        barriers.add(0);

        for(int[] q : queries){
            if(q[0] == 1){
                barriers.add(q[1]);
            }
        }

        List<Integer> positions = new ArrayList<>(barriers);

        for(int i = 1; i < positions.size(); i++){
            update(
                1,
                0,
                MAXI,
                positions.get(i),
                positions.get(i) - positions.get(i - 1)
            );
        }

        List<Boolean> ans = new ArrayList<>();

        for(int i = queries.length - 1; i >= 0; i--){

            if(queries[i][0] == 2){

                int x = queries[i][1];
                int sz = queries[i][2];

                int prevBarrier = barriers.floor(x);

                int bestGap = query(
                    1,
                    0,
                    MAXI,
                    0,
                    prevBarrier
                );

                bestGap = Math.max(bestGap,x - prevBarrier);

                ans.add(bestGap >= sz);
            }
            else{

                int x = queries[i][1];

                int leftBarrier = barriers.lower(x);

                update(1,0,MAXI,x,0);

                Integer rightBarrier = barriers.higher(x);

                if(rightBarrier != null){
                    update(
                        1,
                        0,
                        MAXI,
                        rightBarrier,
                        rightBarrier - leftBarrier
                    );
                }

                barriers.remove(x);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}